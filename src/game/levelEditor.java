package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class levelEditor extends World implements Serializable {
    public ArrayList<editorCurrent> level = new ArrayList<editorCurrent>();

    private static editorCurrent current;

    public levelEditor(){};

    public void initialise(Game game){
        //the initialisation only adds borders so you know the area you can build in
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

    }
    public void setCurrent(editorCurrent c){current = c; level.add(current);}
    public void setCurrentPos(Vec2 pos){if(current != null){current.posUpdate(pos);} }
    public editorCurrent getCurrent(){return current;}

    //all the components of the level are stored in this array so access to it is key
    public ArrayList<editorCurrent> getLevel() {
        return level; }

    public void saveLevel(){
        //this opens the window to select the name of the level
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("levels"));
        chooser.showSaveDialog(null);

        //breaks down the components into leveleditordata which is a collection of numbers so that classes that
        //arent serialisable dont break the code only passing in floats and ints
        ArrayList<Object> data = new ArrayList<Object>();
        for(int i=0; i<level.size();i++){
            levelEditorData d = new levelEditorData(level.get(i).getPos(),level.get(i).getXsize(),level.get(i).getYsize(),level.get(i).getRotation(),level.get(i).getType());
            data.add(d);}

        try{
            FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("Level saved as: "+ chooser.getSelectedFile().getAbsolutePath()+".ser");
        } catch(IOException i){i.printStackTrace();}}}
