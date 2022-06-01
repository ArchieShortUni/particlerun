package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Collector extends StaticBody{
    private int collected =0;
    private Game game;
    private float needed;
    private GameWorld world;
    public Collector(World w, Shape s, Game g,float n) {
        super(w, s);
        game = g;
        needed =n;
        world = (GameWorld)w;
    }
    //called whenever a particle is calculated
    public void collected(){
        collected += 1;
        game.scoreup();
        float level = game.getLevel();
        if(!game.getCompleted()){
        if(collected == needed){

            for(int i=0; i<world.getBarray().size();i++){
                if(!((particle)world.getBarray().get(i)).getIsDestroyed()){
                    game.addtoScore();}}
            if(level== 3 || level == 6){
                game.complete();}
            else if(level == 1 || level == 2)
            {game.goNextLevel();}}}}

    public void seeCol(){
        System.out.println(collected);
    }
    public void setCollected(int col){collected = col;}}
