package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class edMouseHandler extends MouseAdapter {

    private WorldView view;
    private levelEditor led = new levelEditor();


    public edMouseHandler(WorldView view) {
        this.view = view;
    }

    //when the mouse is pressed the currently selected component gets moved there
    public void mousePressed(MouseEvent e) {
        Vec2 pos = view.viewToWorld(e.getPoint());
        led.setCurrentPos(pos);}}

