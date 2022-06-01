package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler extends MouseAdapter {

    private WorldView view;
    private GameWorld w = new GameWorld();
    public MouseHandler(WorldView view) {
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        Vec2 pos = view.viewToWorld(e.getPoint());

        for(int i=0 ; i<w.getBarray().size();i++) {
            particle p = (particle) w.getBarray().get(i);
            p.applyForce((pos.sub(p.getPosition()).mul(3)), p.getPosition());
            p.setAV((float) (Math.random() * ((10 - -10)+1)));}}

    public void mouseReleased(MouseEvent e){
        for(int i=0; i<w.getBarray().size();i++){
            particle p = (particle) w.getBarray().get(i);
        }}}
