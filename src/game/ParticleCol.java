package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class ParticleCol implements CollisionListener {
    private Collector col;

    public ParticleCol(Collector c){this.col = c;}

    @Override
    /*This handles the collisions for all the particles in the game, it is one of the most important classes in the game
    * as if the particles didn't interact with anything there wouldn't be much point to the game*/
    public void collide(CollisionEvent e) {
        /*This is the code for when particles hit any kind of hazard that should destroy them*/
        if(e.getOtherBody() instanceof Hazard){
            /*This tells the other systems there is now one less particle*/
            ((particle) e.getReportingBody()).pdown();
            e.getReportingBody().destroy();
            /*As the hazards are dynamic bodies every time they collide they move so this code is used to
            * instantly reset them as if they never moved at all*/
            e.getOtherBody().setPosition(((Hazard) e.getOtherBody()).getInit());
            ((Hazard) e.getOtherBody()).setLinearVelocity(new Vec2(0,0));
            ((Hazard) e.getOtherBody()).setAngularVelocity(((Hazard) e.getOtherBody()).getAngv());

         }
        /*This is for when the particle hits a collector at the end of the level*/
        else if(e.getOtherBody() instanceof Collector){
            /*keeping count of the amount collected affects the score, how many are left on the level and how many more
            * need to hit the objective*/
            col.collected();
            /*the particle needs to then be destroyed as if it got absorbed by the collector */
            ((particle) e.getReportingBody()).pdown();
            e.getReportingBody().destroy();}

        else if(e.getOtherBody() instanceof particle){
            /*This code is used to stop all the particles clumping together or else they would all just form a ball
            * when the forces are applied making it a very static game. Whenever two particles collide a random
            * equal and opposite force is applied to both rebounding them off of eachother*/
            Vec2 f = new Vec2((float)Math.random() * ((2f - -2f)+0.5f),(float)Math.random() * ((2f - -2f)+0.5f));
            ((particle) e.getOtherBody()).applyForce(f);
            ((particle) e.getReportingBody()).applyForce(f.sub(f.mul(2))); }

        else if(e.getOtherBody() instanceof portal){
            /*The most complicated of all the collisions this takes in the information about the particle such as
            * the velocity, the distance from the center of the portal and the angle it is entering the portal at */
            float ang = (float) Math.toRadians(((portal) e.getOtherBody()).getlinkangle());
            float dif=0;
            /*It is important to know the information about both the portals too otherwise when the particle is moved
            * it wont know what angle it will be coming out at as well as the direction it will be facing.*/
            if(Math.toRadians(e.getOtherBody().getAngle()) >= 0.39269908169){
                dif = e.getOtherBody().getPosition().x - e.getReportingBody().getPosition().x;}
            else{dif = e.getOtherBody().getPosition().y - e.getReportingBody().getPosition().y;}
            Vec2 vel = e.getVelocity();
            /*This line here is a function in the portal class that is responsible for moving the position of the particle
            * and then applies the appropriate forces */
            ((portal) e.getOtherBody()).transfer((particle) e.getReportingBody(),dif,vel,ang);
            /*Much like with every collision this section is used just to reset whatever the particle collides with
            * and in this case this is the portal so it gets completely reset */
            e.getOtherBody().setPosition(((portal) e.getOtherBody()).getInit());
            ((portal) e.getOtherBody()).setLinearVelocity(new Vec2(0,0));
            ((portal) e.getOtherBody()).setAngularVelocity(((portal) e.getOtherBody()).getAngv());
            e.getOtherBody().setAngleDegrees(e.getOtherBody().getAngle());}}}
