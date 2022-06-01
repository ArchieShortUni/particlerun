package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {
    private Game game;
    private Image bg = new ImageIcon("data/bg1.png").getImage();
    private GameWorld tracker = new GameWorld();

    public MyView(World w, int width, int height,Game g) {
        super(w, width, height); game = g;}

    @Override
        protected void paintBackground(Graphics2D g) {
        if(game.getBg() <2){bg = new ImageIcon("data/bg1.png").getImage();}
        else if(game.getBg() <3){bg = new ImageIcon("data/bg2.png").getImage();}
        else if(game.getBg() <4){bg = new ImageIcon("data/bg3.png").getImage();}
        else if(game.getBg() <5){bg = new ImageIcon("data/bg4.png").getImage();}
        else{bg = new ImageIcon("data/bg5.png").getImage();}

        g.drawImage(bg,0,0,this);}

    protected void paintForeground(Graphics2D g) {
        int toCol = game.getToWin() - game.getCollected();
        if(toCol< 0){toCol = 0;}
        if(game.getLevel() > 0) {
            g.drawString("Left to collect: " + toCol, 10, 15);
            g.drawString(game.getScore() + " :Score", 730, 15);
            g.drawString("particles left: " + game.getTotal(), 350, 15);}
        if(game.getCompleted() == true){
            g.drawString("Well done you beat this with a score of "+game.getFinalScore()+" press start to go again or try the level editor",200,317.5f);
        }}}
