package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Hazard extends DynamicBody{
    private Vec2 init;
    private float angv;

    public Hazard(World w, Shape s,Vec2 pos,float v) {
        super(w, s);
        this.setGravityScale(0);
        this.setPosition(pos);
        init = pos;
        this.setAngularVelocity(v);
        this.angv = v;
    }

    public Vec2 getInit(){
        return init;
    }

    public float getAngv(){return angv;}

}
