package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;

/*
* @author Archie Short
* Particle run game
*
* */
public class Game {
    //game variables
    private World world;
    private UserView view;
    private UserView leview;
    private int level;
    private int Collected =0;
    private int score =0;
    private int toWin;
    private int total;
    private JFrame frame;
    private JFrame leframe;
    static private mainMenu men;
    private static String path;
    private boolean completed = false;
    private int finalScore = 0;
    private float bg =0;

    //intial setup
    public Game() {
        level = 0;
        world = new level0();
        ((level0) world).populate(this);
//635
        //setting up frame and view
        view = new MyView(world, 800, 635,this);
        view.addMouseListener(new MouseHandler(view));
        view.addMouseListener(new edMouseHandler(view));
        final JFrame frame = new JFrame("Coursework Game");
        this.frame = frame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        men = new mainMenu(view, this);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.add(men.getMenuPanel(), BorderLayout.SOUTH);
//        frame.remove((men.getMenuPanel()));
        //JFrame debugView = new DebugViewer(world, 500, 700);
        // view.setGridResolution(1);
        frame.addKeyListener(new saveController(this));

        world.start();

    }

// called whenever the user is to change level to the next one
    public void goNextLevel(){
        //random background
        bgGen();
        completed = false;
        view.setGridResolution(0);
        world.stop();
        //for when you complete the last level
        if(level == 3){
            goToMenu();
        }
        else if(level == 1){
            level++;
            world = new level2();
            ((level2) world).populate(this);
            view.setWorld(world);
            world.start();

        }
        else if(level == 2){
            level++;
            world = new level3();
            ((level3) world).populate(this);
            view.setWorld(world);
            world.start();
        }
    }

    public void start(){
        //this is for the start button on the menu panel, start level one and resets your score
        bgGen();
        score = 0;
        completed = false;
        view.setGridResolution(0);
        level = 1;
        world.stop();
        world = new level1();
        ((level1) world).populate(this);
        view.setWorld(world);
        world.start();

    }
    //restart level works on custom and in built levels
    public void restartLevel(boolean loaded){

        bgGen();
        score = 0;
        completed = false;
        System.out.println(level);
        world.stop();
        if(level == 0){
            this.goToMenu();
        }
        else if(level == 1){
            world = new level1();
            ((GameWorld)world).setLoaded(loaded);
            ((level1) world).populate(this);
            view.setWorld(world);
            world.start();
        }
        else if(level == 2){
            world = new level2();
            ((GameWorld)world).setLoaded(loaded);
            ((level2) world).populate(this);
            view.setWorld(world);
            world.start();
        }
        else if(level == 3){
            world = new level3();
            ((GameWorld)world).setLoaded(loaded);
            ((level3) world).populate(this);
            view.setWorld(world);
            world.start();
        }
        else if(level ==6){
            world = new customLevel();
            ((GameWorld)world).setLoaded(loaded);
            ((customLevel)world).populate(this, 20,30,path);
            view.setWorld(world);
            world.start();

        }
    }
    //for when loading in custom levels
    public void load(saveData data){
        completed = false;
        level = data.getLevel();
        restartLevel(true);
        score = (int)data.getScore();
        toWin = (int)data.getNeeded();
        total = (int)data.getTotal();
        Collected = data.getCollected();
        ((GameWorld)world).setOne(data.getCollected());
        ((GameWorld)world).setBarray(data.getParticles());
    }


    public void goToMenu(){
        //used to go back to the main menu
        completed = false;
        view.setGridResolution(0);
        level = 0;
        world.stop();
        world = new level0();
        ((level0) world).populate(this);
        view.setWorld(world);
        world.start();
    }
    //button on the menu panel used to go to the level editor
    public void goToEditor(){
        bgGen();
        completed = false;
        view.setGridResolution(1);
        level = -1;
        world.stop();
        world = new levelEditor();
        ((levelEditor) world).initialise(this);
        view.setWorld(world);
        world.start();

        //leview is the second window that pops up specifically for the level editor
        leview = new MyView(world,750, 37,this);

        leview.addMouseListener(new MouseHandler(leview));

        final JFrame leframe = new JFrame("Editor panel");
        this.leframe = leframe;
        frame.addKeyListener(new editorController((levelEditor)world));
        leframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leframe.setLocationByPlatform(true);
        editorMenu lemen = new editorMenu(this, leview, (levelEditor) world);
        leframe.add(leview);
        leframe.setResizable(false);
        leframe.pack();
        leframe.setVisible(true);
        leframe.add(lemen.getleMenuPanel(), BorderLayout.NORTH);


        //JFrame debugView = new DebugViewer(world, 500, 700);
        // view.setGridResolution(1);

        world.start();
    }
    //this is for traversing to custom levels
    public void goToCustom(String path){
        bgGen();
        completed = false;
        this.path = path;
        view.setGridResolution(0);
        level = 6;
        world.stop();
        world = new customLevel();
        ((customLevel)world).populate(this, 10,30,path);
        view.setWorld(world);
        world.start();

    }
    //whenever a particle goes into the collector score goes up
    public void scoreup(){
        score +=100;
        Collected +=1;

    }
    //clearing certain variables for each new level
    public void clear(int i){
        Collected =0;
        total = i;
    }

    //all the neccesary gets and sets for this class
    public void toWin(int i){
        toWin = i;
    }
    public int getScore(){ return score; }

    public int getCollected(){ return Collected; }

    public int getToWin(){return toWin;}

    public int getTotal(){return total;}

    public void totaldwn(){
        total --;
    }
    public int getLevel(){return level;}
    public JFrame getFrame(){return frame;}
    public void frameDispose(){leframe.dispose();}
    public void complete(){completed = true; finalScore = score;}
    public boolean getCompleted(){return completed;}
    public int getFinalScore(){return  finalScore;}
    public void addtoScore(){score += 25;}
    //chooses a random background
    public void bgGen(){
        bg = (float)(Math.random() * ((6-1)+1))+1;
    }
    public float getBg(){return  bg;}

    public static void main(String[] args) {
        new Game();
    }
}


