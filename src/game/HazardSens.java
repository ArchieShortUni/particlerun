package game;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class HazardSens implements SensorListener {
    private Hazard haz;

    public HazardSens(Hazard h) {
        this.haz = h;
    }
    @Override
    //this is the code for the gravity well where on entering the sensor a force is applied to make it go towards
    // the center of the gwell
    public void beginContact(SensorEvent e) {
        if(e.getContactBody() instanceof particle) {
            particle p = (particle) e.getContactBody();
            p.applyForce((haz.getPosition().sub(p.getPosition()).mul(5f)), p.getPosition());
        }
    }
    //once the particle leaves the area again it also gets a force pushing it back to the center again so it contains any
    //particles trying to escape
    @Override
    public void endContact(SensorEvent e) {
        particle p = (particle) e.getContactBody();
        p.applyForce((haz.getPosition().sub(p.getPosition()).mul(10f)), p.getPosition());}
}
