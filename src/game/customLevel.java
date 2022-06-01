package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.BiPredicate;

public class customLevel extends GameWorld implements Serializable {
    /*this array contains all of the components of a custom level including positions, types and size*/
    private ArrayList<levelEditorData> levelItems = new ArrayList<levelEditorData>();
    /*As you need to have a spawner and collector at minimum for the game to work these two are checked for
    * so these variables exist as a check */
    private levelEditorData spwner;
    private levelEditorData collector;

    public customLevel(){}
    /*I use populate to fill the screen with the needed components rather than doing it all in the constructor*/
    public void populate(Game game, int needed,int max, String path){
        loadData(path);
        /*Searching type 1 and 2 finds the spawner and collector then loads them into the variables above*/
        Search(1);
        Search(2);
        super.populate(game,collector.getxPos(),collector.getyPos(),spwner.getxPos(),spwner.getyPos(),needed,max);
//        System.out.println(collector.getxPos()+ " "+collector.getyPos() +" "+spwner.getxPos()+" "+ spwner.getyPos());
        /*This section is the block the border of the level which is standard across all levels*/
        //Borders
        //
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground1 = new StaticBody(this, groundShape);
        ground1.setPosition(new Vec2(0, -14.5f));

        //ceiling
        Body ceiling1 = new StaticBody(this,groundShape);
        ceiling1.setPosition(new Vec2(0,14.5f));

        //walls

        // BodyImage wallimg = new BodyImage("data/Sprite-0006.png",14.5f);
        Shape wallShape = new BoxShape(0.5f,14.5f);
        //left wall
        Body leftWall1 = new StaticBody(this,wallShape);
        leftWall1.setPosition(new Vec2(-19.5f,0));

        //right wall
        Body rightWall1 = new StaticBody(this,wallShape);
        rightWall1.setPosition(new Vec2(19.5f,0));
        /*This for loop loads in each item from the serialised data and turns them into components on the screen
        * that could be a wall, a spawner, a gravity well*/
        for(int i =0; i<levelItems.size();i++){
            loadComponent(levelItems.get(i));}}


    public void loadData(String path){
        /*The data is stored in an array of "objects" and is must be deserialised before we get the data out*/
        ArrayList<Object> deserialised = new ArrayList<Object>();
        try{
            /*This attempts to open the file using the path passed through as a string and
            * loads it into the deserialised arraylist*/
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserialised = (ArrayList<Object>)in.readObject();
            /*We make sure to close everything after to prevent any wasted resources like a memory leak*/
            in.close();
            fileIn.close();
            /*These are here to catch any errors when opening the file such as corruption or perhaps a file that
            * doesn't exist*/
        }catch(IOException i) {i.printStackTrace(); return;}
        catch(ClassNotFoundException c){
            c.printStackTrace(); return;
        }
        for(int i=0; i<deserialised.size(); i++){
            /*The deserialised array is now sived through adding all the elements to a different array of level data*/
            levelItems.add((levelEditorData) deserialised.get(i));
        }}

    /*This search is used to specifically pick out the spawner and collector*/
    public void Search(float type){
        for(int i = 0; i<levelItems.size();i++){
            /*The "type" is a number assigned with each number meaning a different component
            * 1 is always a spawner for the particles and 2 is the collector*/
            if(levelItems.get(i).getType() == type){
                if(type == 1){spwner = levelItems.get(i);}
                else{collector = levelItems.get(i);}}}}

    public void loadComponent(levelEditorData comp){
        /*this is where the type matters as it seperates out the rest of the data into walls and the various hazards*/
        if(comp.getType() == 3){
            /*This is the gravity well that acts as a black hole sucking in anything that goes into its radius and destroying it
          */
            Shape hazShapeBox = new BoxShape(1f,1f);
            /*You can see the use of comp. which is the component (or the level data) that has
            * get methods attatched so the data can be pulled out to generate bodies */
            Hazard gwell = new Hazard(this,hazShapeBox,new Vec2(comp.getxPos(),comp.getyPos()),0);
            /*The sensor is used for detecting when the particles are within the radius and then applies a force on them
            * acting towards the center of the well */
            Sensor s1 = new Sensor(gwell,new CircleShape(4),1);
            s1.addSensorListener(new HazardSens(gwell));
        }

        else if(comp.getType() == 4){
            /*This is the wall block which is the backbone of the level as it forces you down a certain route
            * facing different obsticles */
            Shape wallShape = new BoxShape(comp.getXsize(),comp.getYsize());
            Body wall = new StaticBody(this,wallShape);
            wall.setPosition(new Vec2(comp.getxPos(),comp.getyPos()));
        }

        else if(comp.getType() == 5){
            Shape hazShapeSpinner = new BoxShape(0.2f, 1.7f);
            /*This is the hazard that spins as a sort of swapping blade */
            Hazard hazardSpin = new Hazard(this,hazShapeSpinner,new Vec2(comp.getxPos(),comp.getyPos()),2);
        }

        else if(comp.getType() == 6){
            /*This is a special kind of hazard where you can shape it like a wall to the size and shape you want in
             * the editor*/
            Shape hazShapeBox = new BoxShape(comp.getXsize(),comp.getYsize());
            Hazard hazardBox = new Hazard(this, hazShapeBox,new Vec2(comp.getxPos(),comp.getyPos()),0); }}}
