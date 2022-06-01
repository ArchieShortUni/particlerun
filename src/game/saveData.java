package game;
import org.jbox2d.common.Vec2;
import java.io.Serializable;
import java.util.ArrayList;

public class saveData implements Serializable {
   private  float score =0;
   private  float needed =0;
   private  float total =0;
   private int collected =0;
   private int level =0;
   private  ArrayList<Vec2> particles = new ArrayList<Vec2>();
   public saveData(){}
   public void update(float s, float n, float t, int c, int l){
       score = s; needed = n; total = t; collected = c; level = l;}
   public void addParticle(Vec2 p){particles.add(p);}
   public float getScore() {
        return score;
    }
    public float getNeeded() {
        return needed;
    }
    public float getTotal() {
        return total;
    }
    public int getLevel(){
       return level;
   }
   public int getCollected(){return collected;}
    public ArrayList<Vec2> getParticles() {
        return particles;
    }
}
