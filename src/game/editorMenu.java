package game;

import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editorMenu {
    private UserView leview;
    private JPanel editorPanel;
    //all these buttons are on the editor panel to spawn components in
    private static JButton spawner = new JButton("Particle spawner");
    private static JButton collector = new JButton("Collector");
    private JButton wall = new JButton("Wall");
    private JButton hazard = new JButton("Hazard Spinner");
    private JButton hazardblock = new JButton("Hazard block");
    private JButton gwell = new JButton("Gravity well");
    private JButton save = new JButton("save");
    private Game game;
    private editorCurrent pholder = new editorCurrent();

    public editorMenu(Game game, UserView view, levelEditor l){
        this.game = game;
        this.leview = view;
        editorPanel = new JPanel();

        editorPanel.add(spawner);
        spawner.setFocusable(false);
        spawner.addActionListener(new ActionListener() {
            @Override
            // to create a spawner
            public void actionPerformed(ActionEvent e) {
                System.out.println("Spawner");
                if(pholder.getSpawner() == null){
                    editorCurrent spawner = new editorCurrent(1,l);
                    spawner.Generate();
                }
                else{pholder.getSpawner().posUpdate(new Vec2(0,0));
                l.setCurrent(pholder.getSpawner());}
            }});
        // to create a collector
        editorPanel.add(collector);
        collector.setFocusable(false);
        collector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(pholder.getCollector());
                if(pholder.getCollector() == null){
                    editorCurrent collector = new editorCurrent(2,l);
                    collector.Generate();
                }
                else{pholder.getCollector().posUpdate(new Vec2(0,0));
                l.setCurrent(pholder.getCollector());}
            }});
        // to create a wall
        editorPanel.add(wall);
        wall.setFocusable(false);
        wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorCurrent wall = new editorCurrent(4,l);
            wall.Generate();}});
        // to create a spinning hazard
        editorPanel.add(hazard);
        hazard.setFocusable(false);
        hazard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorCurrent hazards = new editorCurrent(5,l);
                hazards.Generate();}});
        // to create a hazard block
        editorPanel.add(hazardblock);
        hazardblock.setFocusable(false);
        hazardblock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorCurrent hazardblock = new editorCurrent(6,l);
                hazardblock.Generate();
            }
        });
        // to create a gravity well
        editorPanel.add(gwell);
        gwell.setFocusable(false);
        gwell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("gravity well");
                editorCurrent gwell = new editorCurrent(3,l);
                gwell.Generate();}});

        editorPanel.add(save);
        save.setFocusable(false);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("save");
                l.saveLevel();
                game.frameDispose();
                game.goToMenu();
            }});
    }

    public JPanel getleMenuPanel() {return editorPanel;}
}

