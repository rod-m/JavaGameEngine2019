package core.game_engine;

import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.Rectangle;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Sprite extends GameObject{
    protected BoxCollider2D boxCollider2D;
    protected Rectangle bounds;
    public Sprite(PApplet p, float x, float y, float w, float h){
        super(p);
        this.position = new PVector(x, y, 0);
        this.next_position = new PVector(x, y, 0);
        this.boxCollider2D = new BoxCollider2D(this, w, h);
        this.bounds = this.boxCollider2D.getBounds();
    }
    @Override
    public void update(){
        for(Component c: this.componentList){
            c.update();
        }
    }
}
