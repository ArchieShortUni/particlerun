package game;

import org.jbox2d.common.Vec2;

import java.io.Serializable;
//this is the data that actually gets stored for the level editor

public class levelEditorData implements Serializable {
    //the position of the component
    private float xpos = 0;
    private float ypos = 0;
    //the size (which only applies to walls and hazard boxs)
    private float xsize = 1;
    private float ysize = 1;
    private float rotation =0;

    //type which tells us what the component is
    private float type;
    //populates the class
    public levelEditorData(Vec2 p, float xs, float ys, float rot, float t){
        xpos = p.x; ypos = p.y; xsize = xs; ysize = ys; rotation = rot; type = t;
    }
    //these getters are used when loading back in
    public float getxPos() {
        return xpos;
    }
    public float getyPos(){
        return ypos;
    }
    public float getXsize() {
        return xsize;
    }
    public float getYsize() {
        return ysize;
    }
    public float getType() {
        return type;
    }
}
