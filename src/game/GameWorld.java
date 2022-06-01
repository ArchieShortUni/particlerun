package game;
import java.util.ArrayList;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    public static ArrayList<Body> barray;
    private Collector one;
    private float spawnx;
    private float spawny;
    private Game game;
    private boolean loaded = false;
    public GameWorld(){}

    public void populate(Game game,float colx, float coly, float spawnx, float spawny, int needed,int max){
        game.clear(max);
        game.toWin(needed);
        this.spawnx = spawnx;
        this.spawny = spawny;
        this.game = game;
        BodyImage col = new BodyImage("data/col.png",1.6f);
        Shape ColShape = new BoxShape(0.8f,0.8f);
        one = new Collector(this,ColShape,game,needed);
        one.addImage(col);


        one.setPosition(new Vec2(colx,coly));

        if(!loaded){
        barray = new ArrayList<Body>();
        for(int b=0; b<max;b++){
            barray.add(new particle(this, one,game));
            barray.get(b).setPosition(new Vec2(spawnx-.5f+((float)(Math.random() * (2 - -2)+1)),spawny-.5f+((float)(Math.random() * (2 - -2)+1))));
            barray.get(b).addCollisionListener(new ParticleCol(one));
            //barray.get(b).addImage(part);
        }
            System.out.println(" no loader");}
    }
    //used to manipulate the array of particles
    public ArrayList<Body> getBarray() {
        return barray;
    }
    public void setBarray(ArrayList<Vec2> list){

        barray = new ArrayList<Body>();
        for(int b=0; b<list.size();b++){

            barray.add(new particle(this, one,game));
            barray.get(b).setPosition(list.get(b));
            barray.get(b).addCollisionListener(new ParticleCol(one));
            //barray.get(b).addImage(part);
        }
    this.setLoaded(false);
    System.out.println("loader");}
    //sets the collector variables
    public void setOne(int c){this.one.setCollected(c);}
    //this is to change if the level is loaded from a save or normally so that it doesnt try do the normal particle spawn
    public void setLoaded(boolean b){loaded = b;}

}
