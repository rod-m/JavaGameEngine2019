package core.game;
import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Platform extends Sprite {
    public PVector size;
    public Platform(PApplet p, int x, int y, int w, int h){
        super(p, x,y,w,h);
        this.parent = p;
        this.size = new PVector(w,h,0);
        this.position = new PVector(x, y, 0);
    }
   @Override
    public void update(){
        super.update();
        parent.pushMatrix();
       parent.rectMode(PApplet.CENTER);
       // platform rectangle
       parent.translate(this.position.x, this.position.y);
        this.parent.rect(0, 0, this.size.x,this.size.y);

        parent.popMatrix();
    }

}
