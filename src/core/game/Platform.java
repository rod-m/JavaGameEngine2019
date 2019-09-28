package core.game;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Platform extends GameObject {
    public PVector size;
    public Platform(PApplet p, int x, int y, int w, int h){
        this.parent = p;
        this.size = new PVector(w,h,0);
        this.position = new PVector(x, y, 0);
    }
   @Override
    public void update(){
       // platform rectangle
        this.parent.rect(this.position.x, this.position.y, this.size.x,this.size.y);
    }

}
