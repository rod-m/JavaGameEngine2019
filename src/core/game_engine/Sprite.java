package core.game_engine;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Sprite extends GameObject{
    public Sprite(PApplet p, float x, float y, float w, float h){
        super(p);
        this.position = new PVector(x, y, 0);
    }
    @Override
    public void update(){
        for(Component c: this.componentList){
            c.update();
        }
    }
}
