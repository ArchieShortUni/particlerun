package game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class saveController extends KeyAdapter {
    private Game game;
    GameWorld gw = new GameWorld();
    public saveController(Game g){game=g;}

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_S){
            saveData s = new saveData();
            s.update(game.getScore(),game.getToWin(),game.getTotal(),game.getCollected(), game.getLevel());
            for(int i=0;i<gw.getBarray().size();i++){
                //HERE
                if(!((particle)gw.getBarray().get(i)).getIsDestroyed()){
                s.addParticle(gw.getBarray().get(i).getPosition());}}

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("saves"));
            chooser.showSaveDialog(null);
            ArrayList<Object> data = new ArrayList<Object>();
            data.add(s);

            try{
                FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath()+".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(data);
                out.close();
                fileOut.close();
                System.out.println("Level saved as: "+ chooser.getSelectedFile().getAbsolutePath());
            } catch(IOException i){i.printStackTrace();}

        }
        else if(key == KeyEvent.VK_L){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("saves"));
            int result = fileChooser.showOpenDialog(game.getFrame());
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file" + selectedFile.getPath());
                ArrayList<Object> deserialised = new ArrayList<Object>();

                try{
                    FileInputStream fileIn = new FileInputStream(selectedFile.getPath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    deserialised = (ArrayList<Object>)in.readObject();
                    in.close();
                    fileIn.close();
                }catch(IOException i) {i.printStackTrace(); return;}
                catch(ClassNotFoundException c){
                    c.printStackTrace(); return;
                }
                System.out.println("Loaded: score "+  ((saveData)deserialised.get(0)).getScore()+ " needed "+((saveData)deserialised.get(0)).getNeeded()+" total "+((saveData)deserialised.get(0)).getTotal()+" collected "+((saveData)deserialised.get(0)).getCollected());
                game.load((saveData)deserialised.get(0));
            }}}}

