package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.GameObject;
import core.game_engine.Sprite;
import processing.core.PVector;

public class PhysicsComponent extends Component {
    private PVector velocity = new PVector(0,0,0);
    public float maxSpeed = 5f;
    private float friction = 0.9f;
    // box collider
    private BoxCollider2D boxCollider2D;
    public PhysicsComponent(GameObject g, BoxCollider2D b){
        super(g);
        this.boxCollider2D = b;

    }

    @Override
    protected void update() {
        if(this.velocity.mag() > this.maxSpeed){
            this.velocity.setMag(this.maxSpeed);
        }
       if(this.boxCollider2D.otherColliders.size() > 0){
           for(BoxCollider2D b : this.boxCollider2D.otherColliders){
               // move player relative to what it collided with
               velocity.x = 0;
               velocity.y = 0;
               System.out.println("collided!");
           }
           this.boxCollider2D.otherColliders.clear();
       }
       this.velocity.mult(friction);
        this.gameObject.position.add(this.velocity);
    }
    public void setVelocity(float x, float y){
        this.velocity.x += x;
        this.velocity.y += y;
    }
}
