package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.io.Serializable;

public class editorCurrent implements Serializable {
    private Vec2 pos;
    private float xsize = 1;
    private float ysize = 1;
    private float rotation =0;
    private float type;
    private static editorCurrent spawner;
    private static editorCurrent collector;
    private levelEditor world;
    private Body holderb;



    public editorCurrent(){};
    //constructer of the editor current which is used in the level editor to represent level components
    public editorCurrent(float type, levelEditor world){
        xsize = 1; ysize = 1;
        this.type = type;
        this.world = world;
    }

    public void Generate(){
        world.setCurrent(this);
        //p spawner, collector, g well, wall, hazard, portal, rotate
        if(type == 1){xsize = 0.2f; ysize = 0.2f; spawner = this;}

        else if(type ==2){xsize = 0.8f; ysize = 0.8f; collector = this;}
        else if(type ==3){xsize = 0.8f; ysize = 0.8f;}
        else{xsize = 1; ysize = 1;}

        if(type <=4 || type == 6){
            Shape spnShape = new BoxShape(xsize,ysize);
            holderb = new StaticBody(world, spnShape);
            System.out.println("spawn test");
        }

        else if(type == 5 ){
            xsize = 0.2f; ysize = 1.7f;
            Shape hazShape = new BoxShape(xsize,ysize);
            holderb = new StaticBody(world, hazShape);
        }
        posUpdate(new Vec2(0,0));
    }
    public void posUpdate(Vec2 p){
        pos = p;
        holderb.setPosition(p);
        System.out.println("Type"+this.getType()+" Position "+p);
    }
    //this is for changing the size of walls and hazards in the x and y axis
    public void scale(float x, float y){
        pos = holderb.getPosition();
        holderb.destroy();
        xsize += x*0.2;
        ysize += y*0.2;
        Shape scaleShape = new BoxShape(xsize,ysize);
        holderb = new StaticBody(world, scaleShape);
        holderb.setPosition(pos);
    }
    public void rotate(){
        holderb.setAngleDegrees(holderb.getAngleDegrees()+90);
    }
    public float getType(){return type;}

    public Vec2 getPos() {
        return pos;
    }

    public float getXsize() {
        return xsize;
    }

    public float getYsize() {
        return ysize;
    }

    public float getRotation() {
        return rotation;
    }

    public editorCurrent getSpawner(){return spawner;}
    public editorCurrent getCollector(){return collector;}
}
