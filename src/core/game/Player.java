package core.game;
import core.game_engine.input_commands.MoveAble;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Player extends GameObject implements MoveAble {
    public PVector size;

    public Player(PApplet p, int x, int y, int w, int h) {
        this.parent = p;
        this.size = new PVector(w, h, 0);
        this.position = new PVector(x, y, 0);
    }
    @Override
    public void moveLeft(){
        // move player
        this.position.x -= 1;
    }
    @Override
    public void moveRight(){
        // move player
        this.position.x += 1;
    }

    @Override
    public void moveUp() {
        this.position.y -= 1;
    }

    @Override
    public void moveDown() {

    }

    @Override
    public void update() {

        // platform rectangle
        this.parent.rect(this.position.x, this.position.y, this.size.x, this.size.y);
    }
}