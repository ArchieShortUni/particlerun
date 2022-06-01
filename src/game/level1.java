package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import city.cs.engine.Shape;
public class level1 extends GameWorld{

    public void populate(Game game){
        super.populate(game,-18,12,-18,-15,10,30);
//        BodyImage groundimg = new BodyImage("data/Sprite-0004.png",1f);

        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground1 = new StaticBody(this, groundShape);
        Body ground2 = new StaticBody(this,groundShape);
        ground1.setPosition(new Vec2(-10, -14.5f));
        ground2.setPosition(new Vec2(10,-14.5f));


        //ceiling
        Body ceiling1 = new StaticBody(this,groundShape);
        Body ceiling2 = new StaticBody(this,groundShape);
        ceiling1.setPosition(new Vec2(-10,14.5f));
        ceiling2.setPosition(new Vec2(10,14.5f));

        //maze walls
//        BodyImage mazeimg = new BodyImage("data/mwall.png",1.2f);

        //layer one
        Shape mazeShape = new BoxShape(10.2f, 0.6f);
        Body m1 = new StaticBody(this, mazeShape);
        m1.setPosition(new Vec2(-8.8f,-10));
       // m1.addImage(mazeimg);

        Body m2 = new StaticBody(this, mazeShape);
        m2.setPosition(new Vec2(3f,-10));
       // m2.addImage(mazeimg);

        //layer 2
        Body m3 = new StaticBody(this, mazeShape);
        m3.setPosition(new Vec2(8.8f,-5));
        //m3.addImage(mazeimg);

        Body m4 = new StaticBody(this, mazeShape);
        m4.setPosition(new Vec2(-3f,-5));
        //m4.addImage(mazeimg);

        //layer 3
        Body m5 = new StaticBody(this, mazeShape);
        m5.setPosition(new Vec2(-8.8f,0));
        //m5.addImage(mazeimg);

        Body m6 = new StaticBody(this, mazeShape);
        m6.setPosition(new Vec2(3f,0));
       // m6.addImage(mazeimg);

        //layer 4
        Body m7 = new StaticBody(this, mazeShape);
        m7.setPosition(new Vec2(8.8f,5));
       // m7.addImage(mazeimg);

        Body m8 = new StaticBody(this, mazeShape);
        m8.setPosition(new Vec2(-3f,5));
       // m8.addImage(mazeimg);

        //layer 5
        Body m9 = new StaticBody(this, mazeShape);
        m9.setPosition(new Vec2(-8.8f,10));
        //m9.addImage(mazeimg);

        Body m10 = new StaticBody(this, mazeShape);
        m10.setPosition(new Vec2(3f,10));
       // m10.addImage(mazeimg);


        //walls

//        BodyImage wallimg = new BodyImage("data/Sprite-0006.png",14.5f);
        Shape wallShape = new BoxShape(0.5f,14);
        //left wall
        Body leftWall1 = new StaticBody(this,wallShape);
        Body leftwall2 = new StaticBody(this,wallShape);
        leftWall1.setPosition(new Vec2(-19.5f,-7));
        leftwall2.setPosition(new Vec2(-19.5f,7));
        // leftWall1.addImage(wallimg);
        //leftwall2.addImage(wallimg);
        //right wall
        Body rightWall1 = new StaticBody(this,wallShape);
        Body rightWall2 = new StaticBody(this,wallShape);
        rightWall1.setPosition(new Vec2(19.5f,7));
        rightWall2.setPosition(new Vec2(19.5f,-7));
        //rightWall1.addImage(wallimg);
       // rightWall2.addImage(wallimg);

        //hazard generation
        //the distinction between spinner and box are just the shape and then giving one angular velocity
        Shape hazShapeSpinner = new BoxShape(0.2f, 1.7f);
        Shape hazShapeBox = new BoxShape(1.5f,0.5f);
        Hazard h1 = new Hazard(this,hazShapeSpinner,new Vec2(0,-7.5f),2);

        Hazard h2 = new Hazard(this,hazShapeSpinner,new Vec2(5,-2.5f),2);

        Hazard h3 = new Hazard(this,hazShapeSpinner,new Vec2(-5,-2.5f),2);

        Hazard h4 = new Hazard(this, hazShapeBox,new Vec2(14.5f,0),0);

        Hazard h5 = new Hazard(this,hazShapeBox,new Vec2(-17.5f,5),0);

        Hazard h6 = new Hazard(this,hazShapeSpinner,new Vec2(0,7.5f),8);

        Hazard h7 = new Hazard(this,hazShapeBox,new Vec2(14.5f,10),0);}}
