package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class portal extends DynamicBody {
    /*Lots of information needs to be stored about the portal not only for entry but also exit as all portals are capable of
    * two way transport so they act as entrances and exits*/
    private Vec2 init;
    private float angv;
    /*This is linked portal that is associated with this one. Every portal needs a link even if the link is just itself
    * or the particle won't know what forces need to applied to it and where it goes*/
    private portal link;
    /*The portals are capable of being set to any angle however I didn't quite have time to make the forces work for that
    * but the position works, so in practice the portals are only used in 90 degree increments*/
    private float angle;
    private float entry;

    /*The constructor is fairly basic as all it serves as filling in all the required information*/
    public portal(World w, Shape s, Vec2 i, float a, float ang, float e){
        super(w,s);
        this.init = i; this.setPosition(i);
        this.angv = a; this.setAngularVelocity(a);
        this.setGravityScale(0);
        this.angle = ang; this.setAngleDegrees(ang);
        this.entry = e/1.4f;}
    /*This one line is extremely important as all portals need to be linked and this easy process of doing it in one line
    * saves a lot of time */
    public void applylink(portal p){
        this.link = p;
    }

    /*This is the transfer function that takes care of moving the particle and then applying the correct force on it depending on angle*/
    public void transfer(particle p, float dif, Vec2 vel,float ang){
        /*This multiplier is used to determine what direction the particle comes out of the portal at i.e the left or right side.
        * this is especially important when portals are up against walls or else the particle would just get shot into and stuck into the wall
        * */
        float multiplyer =1;
        if(this.getlinkangle() != 0){multiplyer=-1;}
        /*The x is responsible for the direction of which the particle comes out the portal*/
        float x = link.getInit().x + (link.entry);
        /*The y is equivalent to how high or low from the center the particle entered at regardless of the x value,
        * it will then come out at that same spot but opposite on the opposing portal*/
        float y = link.getInit().y - dif;
        /*The position is calculated using trigonometry ,getting the difference between two points and using an angle to decide where about it will come out*/
        Vec2 pos = new Vec2((float)(Math.cos(ang)*(x - link.getInit().x) - Math.sin(ang) *(y-link.getInit().y) + link.getInit().x), (float)(Math.sin(ang)*(x - link.getInit().x) + Math.cos(ang) * (y-link.getInit().y) + link.getInit().y) );
        p.setPosition(pos);
        /*after the position is set above the force now needs to be applied to the particle, if the link has the same angle as the entry portal this is an easy calculation however
        * the chunk of code after the initial if (which is checking if they are the same angle) handles the case that they do not share the same angle */
        if(Math.toRadians(this.getlinkangle()) != Math.toRadians(this.getAngle())) {
            x = link.getInit().x + (vel.x);
            y = link.getInit().y + vel.y;
            /*This calculation is pretty much the same as for the position but for the veloctiy*/
            vel = new Vec2((float) (Math.cos(ang) * (x - link.getInit().x) - Math.sin(ang) * (y - link.getInit().y) + link.getInit().x), (float) (Math.sin(ang) * (x - link.getInit().x) + Math.cos(ang) * (y - link.getInit().y) + link.getInit().y));
            p.setLinearVelocity(vel.mul(multiplyer));
        }
        else{p.setLinearVelocity(vel.mul(-1));}}

    /*This set of returns are all the information needed to be known about the link portal to assure the particle is transferred correctly*/
    public Vec2 getInit(){ return init; }
    public float getAngv(){ return angv; }
    public float getAngle(){return angle;}
    public float getlinkangle(){return link.getAngle();}
}
