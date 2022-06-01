package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import org.w3c.dom.events.Event;
import java.util.ArrayList;
import java.util.EventListener;

//this is the class for the main menu, there isn't really much to say about this as its all for aethetics sakes
public class level0 extends GameWorld{
    public void populate(Game game){
        super.populate(game,-30,0,-18,25,10,60);
//        BodyImage groundimg = new BodyImage("data/Sprite-0004.png",1f);
        BodyImage titleimg = new BodyImage("data/title-export.png",10);
        Shape title = new BoxShape(5,2);
        Body titleb = new StaticBody(this, title);
        titleb.setPosition(new Vec2(5, 0));
        titleb.addImage(titleimg);
        Shape portalShape = new BoxShape(0.21f,2f);

        //this is just purely for aethetics to give the affect an endless amount of particles are falling in the menu
        portal p1 = new portal(this,portalShape, new Vec2(-16,29),0,90,-1);
        portal p2 = new portal(this,portalShape, new Vec2(-16,-22),0,90,1);

        portal p3 = new portal(this,portalShape, new Vec2(-20,29),0,90,-1);
        portal p4 = new portal(this,portalShape, new Vec2(-20,-22),0,90,1);

        portal p5 = new portal(this, portalShape, new Vec2(-24,29),0,90,-1);
        portal p6 = new portal(this, portalShape, new Vec2(-24,-22),0,90,1);

        p1.applylink(p2);
        p2.applylink(p1);
        p3.applylink(p4);
        p4.applylink(p3);
        p5.applylink(p6);
        p6.applylink(p5);

        }
}

