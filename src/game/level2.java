package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import city.cs.engine.Shape;

public class level2 extends GameWorld {

    public level2() { }

    public void populate(Game game){
        super.populate(game,16,-6,-18,-15,10,30);
//        BodyImage groundimg = new BodyImage("data/Sprite-0004.png",1f);
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground1 = new StaticBody(this, groundShape);
        ground1.setPosition(new Vec2(0, -14.5f));

        //ceiling
        Body ceiling1 = new StaticBody(this,groundShape);
        ceiling1.setPosition(new Vec2(0,14.5f));

        Shape divider = new BoxShape(0.5f, 2);
        Shape divider2 = new BoxShape(0.5f, 5.55f);
        Shape divider3 = new BoxShape(8.45f,0.5f);
        Shape divider4 = new BoxShape(7,0.5f);
        Shape divider5 = new BoxShape(3,0.5f);
        Shape divider6 = new BoxShape(0.5f, 6.5f);

        Body div1 = new StaticBody(this,divider);
        div1.setPosition(new Vec2(-9.5f,-12));

        Body div2 = new StaticBody(this, divider);
        div2.setPosition(new Vec2(9.5f, -12));

        Body div3 = new StaticBody(this, divider2);
        div3.setPosition(new Vec2(2.55f,-4.3f));

        Body div4 = new StaticBody(this, divider2);
        div4.setPosition(new Vec2(-2.55f,-4.3f));

        Body div5 = new StaticBody(this, divider3);
        div5.setPosition(new Vec2(10.5f,-9.45f));

        Body div6 = new StaticBody(this, divider3);
        div6.setPosition(new Vec2(-10.5f,-9.45f));

        Body div8 = new StaticBody(this, divider3);
        div8.setPosition(new Vec2(10.5f,-2));

        Body div7 = new StaticBody(this,divider4);
        div7.setPosition(new Vec2(-12,-5f));

        Body div9 = new StaticBody(this, divider5);
        div9.setPosition(new Vec2(-6,0));

        Body div10 = new StaticBody(this,divider5);
        div10.setPosition(new Vec2(-16,0));

        Body div11 = new StaticBody(this,divider5);
        div11.setPosition(new Vec2(0,.75f));

        Body div12 = new StaticBody(this, divider6);
        div12.setPosition(new Vec2(-2.55f,7.5f));

        Body div13 = new StaticBody(this, divider6);
        div13.setPosition(new Vec2(2.55f,7.5f));

        //walls

       // BodyImage wallimg = new BodyImage("data/Sprite-0006.png",14.5f);
        Shape wallShape = new BoxShape(0.5f,14.5f);
        //left wall
        Body leftWall1 = new StaticBody(this,wallShape);
        leftWall1.setPosition(new Vec2(-19.5f,0));

        //right wall
        Body rightWall1 = new StaticBody(this,wallShape);
        rightWall1.setPosition(new Vec2(19.5f,0));

        //portals
        Shape portalShape = new BoxShape(0.21f,2f);
        portal p1 = new portal(this,portalShape,new Vec2(-10,-12),0,0,-1);
        portal p2 = new portal(this,portalShape,new Vec2(10,-12),0,0,1);
        portal p3 = new portal(this,portalShape,new Vec2(0,0),0,90,-1);
        portal p4 = new portal(this,portalShape,new Vec2(0,-14f),0,90,1);
        portal p5 = new portal(this,portalShape,new Vec2(-15,-5.5f),0,90,-1);
        portal p6 = new portal(this,portalShape,new Vec2(-3,7.5f),0,0,-1);
        portal p7 = new portal(this,portalShape,new Vec2(3,7.5f),0,0,1);
        portal p8 = new portal(this,portalShape,new Vec2(7.5f,-1.5f),0,90,1);
        portal p9 = new portal(this,portalShape,new Vec2(7.5f,-2.5f),0,90,-1);

        p1.applylink(p2);
        p2.applylink(p3);
        p3.applylink(p2);
        p4.applylink(p5);
        p5.applylink(p4);
        p6.applylink(p7);
        p7.applylink(p6);
        p8.applylink(p9);
        p9.applylink(p8);

        //hazards
        Shape hazShapeSpinner = new BoxShape(0.2f, 1.7f);
        Shape hazShapeBox = new BoxShape(1.5f,0.5f);
        Hazard h1 = new Hazard(this,hazShapeSpinner,new Vec2(-5,-12f),2);
        Hazard h2 = new Hazard(this,hazShapeSpinner,new Vec2(5,-12f),-2);

        Hazard h3 = new Hazard(this, hazShapeSpinner,new Vec2(-14,-2.5f),2);
        Hazard h4 = new Hazard(this, hazShapeSpinner,new Vec2(-8,-2.5f),-2);}}
