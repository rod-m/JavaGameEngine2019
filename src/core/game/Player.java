package core.game;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.input_commands.MoveAble;
import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Player extends Sprite implements MoveAble {
    public PVector size;

    private PhysicsComponent physicsComponent;
    private float acceleration = 2f;
    private float jump = 15f;

    public Player(PApplet p, int x, int y, int w, int h) {

        super(p, x, y, w, h);
        this.parent = p;
        this.type = "Player";
        this.size = new PVector(w, h, 0);
        layerType = LayerTypes.MOVING;
        boxCollider2D = new BoxCollider2D(this, w, h);
        physicsComponent = new PhysicsComponent(this, boxCollider2D);
    }

    @Override
    public void update() {
        super.update();
        parent.pushMatrix();
        // platform rectangle
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        parent.fill(255);
        this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }

    @Override
    public void moveLeft() {
        //System.out.println("left?");
        //this.position.x -= 1;
        physicsComponent.setVelocity(-acceleration, 0);
    }

    @Override
    public void moveRight() {
        physicsComponent.setVelocity(acceleration, 0);
    }

    @Override
    public void moveUp() {
        if(this.physicsComponent.isGrounded()){
            physicsComponent.setVelocity(0, -jump);
        }

    }

    @Override
    public void moveDown() {
        physicsComponent.setVelocity(0, acceleration);
    }
}