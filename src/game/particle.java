package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class particle extends DynamicBody{

private static final Shape box = new BoxShape(0.1f,0.1f);
private boolean isDestroyed = false;

private Game game;

    public particle(World w, Collector col, Game g) {
        super(w, box);
        this.addCollisionListener();
        game = g;}

    private void addCollisionListener() {}
    public void setAV(float v){this.setAngularVelocity(v);}
    public void pdown(){game.totaldwn();isDestroyed = true;}
    public boolean getIsDestroyed(){return  isDestroyed;}
}
