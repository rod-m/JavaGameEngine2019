package core.game;
import core.game_engine.Sprite;
import core.game_engine.input_commands.MoveAble;
import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Player extends Sprite implements MoveAble {
    public PVector size;
    private PhysicsComponent physicsComponent;
    public float acceleration = 2f;
    public Player(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.size = new PVector(w, h, 0);
        physicsComponent = new PhysicsComponent(this, this.boxCollider2D);
    }

    @Override
    public void update() {
        super.update();
        // platform rectangle
        parent.pushMatrix();
            parent.rectMode(PApplet.CENTER);
            parent.translate(this.position.x, this.position.y);
            parent.fill(0,0,200, 200);
            this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }

    @Override
    public void moveLeft() {
        //System.out.println("left?");
        this.physicsComponent.setVelocity(-acceleration, 0);
    }

    @Override
    public void moveRight() {
        this.physicsComponent.setVelocity(acceleration, 0);
    }

    @Override
    public void moveUp() {
        this.physicsComponent.setVelocity(0, -acceleration);
    }

    @Override
    public void moveDown() {
        this.physicsComponent.setVelocity(0, acceleration);
    }
}