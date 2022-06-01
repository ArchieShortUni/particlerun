package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class editorController extends KeyAdapter{
    levelEditor lev;
    public editorController(levelEditor lE){lev = lE;}

    @Override
    public void keyPressed(KeyEvent e){
        editorCurrent manipulate = lev.getCurrent();
        //wont try to execute if there is no subject
        if(manipulate != null){
        if(manipulate.getType() ==4 || manipulate.getType() == 6){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){manipulate.scale(0,1);}
        //controls the size of the wall / hazard allowing manipulation of both axis
        else if(key == KeyEvent.VK_DOWN){manipulate.scale(0,-1);}
        else if(key == KeyEvent.VK_LEFT){manipulate.scale(-1,0);}
        else if(key == KeyEvent.VK_RIGHT){manipulate.scale(1,0);}
        else if(key == KeyEvent.VK_R){lev.getCurrent().rotate();}
    }}}
}
