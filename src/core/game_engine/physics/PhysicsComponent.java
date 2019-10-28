package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.GameObject;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import processing.core.PVector;

public class PhysicsComponent extends Component {
    private PVector velocity = new PVector(0,0,0);
    public float maxSpeed = 5f;
    private float friction = 0.9f;
    private float spacer = 0.3f;
    private float gravity = 1.1f;
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
               if(b.gameObject.layerType == LayerTypes.INTERACTABLE){
                   // do something
                   b.gameObject.isActive = false;
               }else{
                   setCollisionSide(b);
               }

               //System.out.println("collided!");
           }
           this.boxCollider2D.otherColliders.clear();
       }
       this.velocity.mult(friction);
       this.gameObject.position = this.gameObject.next_position.copy();
        this.gameObject.next_position.add(this.velocity);
    }
    public void setVelocity(float x, float y){
        this.velocity.x += x;
        this.velocity.y += y;
    }
    private void setCollisionSide(BoxCollider2D otherBox2D){
        // find side from other box
        // todo include new method call
        Point otherTopRight = otherBox2D.bounds.getTopRight();
        Point otherBottomLeft = otherBox2D.bounds.getBottomLeft();
       this.boxCollider2D.findCollisionSide(otherBox2D);
       switch (this.boxCollider2D.hitSide){
           case TOP:
               this.velocity.y = gravity;
               this.gameObject.next_position.y = otherBottomLeft.getY() + this.boxCollider2D.bounds.getHeight() / 2f + spacer;
               break;
           case BOTTOM:
                // stop
               this.velocity.y = 0;
               this.gameObject.next_position.y = otherTopRight.getY() - this.boxCollider2D.bounds.getHeight() / 2f - spacer;
               break;
           case LEFT:
               this.velocity.x = 0;
               this.gameObject.next_position.x = otherBottomLeft.getX() - this.boxCollider2D.bounds.getWidth() / 2f - spacer;
               break;
           case RIGHT:
               this.velocity.x = 0;
               this.gameObject.next_position.x = otherTopRight.getX() + this.boxCollider2D.bounds.getWidth() / 2f + spacer;
               break;
       }
    }
}
