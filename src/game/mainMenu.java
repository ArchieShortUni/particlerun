package game;

import city.cs.engine.UserView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class mainMenu {
    private UserView view;
    private JPanel menuPanel;
    private JButton start = new JButton("start");
    private JButton levelEditor = new JButton("level editor");
    private JButton levelSelect = new JButton("level select");
    private JButton restart = new JButton("restart");
    private JButton quit = new JButton("quit");
    private Game game;

    public mainMenu(UserView v, Game g){
        menuPanel = new JPanel();
        this.view = v;
        this.game = g;

        menuPanel.add(start);
        start.setFocusable(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {g.start();}});

        menuPanel.add(restart);
        restart.setFocusable(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {g.restartLevel(false);}});


        menuPanel.add(levelSelect);
        levelSelect.setFocusable(false);
        levelSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("levels"));
                int result = fileChooser.showOpenDialog(menuPanel);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file" + selectedFile.getPath());
                    game.goToCustom(selectedFile.getPath());
                }
            }});

        menuPanel.add(levelEditor);
        levelEditor.setFocusable(false);
        levelEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { g.goToEditor();}});

        menuPanel.add(quit);
        quit.setFocusable(false);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });}
    public JPanel getMenuPanel(){
        return menuPanel;
    }}
