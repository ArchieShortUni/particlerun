package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import city.cs.engine.Shape;



public class level3 extends GameWorld {

    public level3() {

    }

    public void populate(Game game){
        super.populate(game,18,12,-18,-15,1,30);
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

        //dividers / maze wall
        Shape divider = new BoxShape(8.5f, 0.5f);

        Body div1 = new StaticBody(this,divider);
        div1.setPosition(new Vec2(-10.5f,-9));

        Body div2 = new StaticBody(this,divider);
        div2.setPosition(new Vec2(10.5f,-9));

        Body div3 = new StaticBody(this,divider);
        div3.setPosition(new Vec2(-10.5f,-5));

        Body div4 = new StaticBody(this,divider);
        div4.setPosition(new Vec2(10.5f,-5));

        Body div5 = new StaticBody(this,divider);
        div5.setPosition(new Vec2(-10.5f,0.75f));

        Body div6 = new StaticBody(this,divider);
        div6.setPosition(new Vec2(10.5f,0.75f));

        Body div7 = new StaticBody(this,divider);
        div7.setPosition(new Vec2(-10.5f,4.75f));

        Body div8 = new StaticBody(this,divider);
        div8.setPosition(new Vec2(10.5f,4.75f));


        //gravity well

        BodyImage gwell = new BodyImage("data/gwell.png",1.6f);

        Shape hazShapeBox = new BoxShape(1f,1f);
        Hazard h1 = new Hazard(this,hazShapeBox,new Vec2(5,-7),0);
        Sensor s1 = new Sensor(h1,new CircleShape(4),1);
        s1.addSensorListener(new HazardSens(h1));
        h1.addImage(gwell);

        Hazard h2 = new Hazard(this, hazShapeBox,new Vec2(-5,-7),0);
        Sensor s2 = new Sensor(h2,new CircleShape(4),1);
        s2.addSensorListener(new HazardSens(h2));
        h2.addImage(gwell);

        Hazard h3 = new Hazard(this, hazShapeBox,new Vec2(-5,2.75f),0);
        Sensor s3 = new Sensor(h3,new CircleShape(4),1);
        s3.addSensorListener(new HazardSens(h3));
        h3.addImage(gwell);

        Hazard h4 = new Hazard(this, hazShapeBox,new Vec2(5,2.75f),0);
        Sensor s4 = new Sensor(h4,new CircleShape(4),1);
        s4.addSensorListener(new HazardSens(h4));
        h4.addImage(gwell);

        Hazard h5 = new Hazard(this, hazShapeBox,new Vec2(0,12f),0);
        Sensor s5 = new Sensor(h5,new CircleShape(4),1);
        s5.addSensorListener(new HazardSens(h5));
        h5.addImage(gwell);

        //portals
        Shape portalShape = new BoxShape(0.21f,2f);

          portal p1 = new portal(this,portalShape, new Vec2(18.75f,-11.75f),0,0,-1);
          portal p2 = new portal(this,portalShape, new Vec2(18.75f,-2.25f),0,0,-1);
          portal p3 = new portal(this, portalShape,new Vec2(-18.75f,-2.25f),0,0,1);
          portal p4 = new portal(this, portalShape,new Vec2(-18.75f,7.5f),0,0,1);

        p1.applylink(p2);
        p2.applylink(p1);
        p3.applylink(p4);
        p4.applylink(p3);}}
