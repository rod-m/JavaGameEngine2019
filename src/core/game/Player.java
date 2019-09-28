package core.game;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Player extends GameObject {
    public PVector size;

    public Player(PApplet p, int x, int y, int w, int h) {
        this.parent = p;
        this.size = new PVector(w, h, 0);
        this.position = new PVector(x, y, 0);
    }

    public void move(){
        // move player
        // use AWSD to move player
        if(parent.keyPressed){
            if(parent.key == 'a'){
                // move left
                this.position.x -= 1;
            }
            if(parent.key == 'd'){
                // move right
                this.position.x += 1;
            }
        }
    }

    @Override
    public void update() {
        move();
        // platform rectangle
        this.parent.rect(this.position.x, this.position.y, this.size.x, this.size.y);
    }
}