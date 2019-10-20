package core.game_engine;

import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Sprite extends GameObject {
    public BoxCollider2D boxCollider2D;
    public Sprite(PApplet p, float x, float y, float w, float h){
        super(p);
        this.position = new PVector(x, y, 0);
        this.next_position = new PVector(x, y, 0);
    }

    @Override
    public void update() {
        for(Component c: this.componentList){
            c.update();
        }
    }
}
