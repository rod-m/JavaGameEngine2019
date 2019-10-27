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
               if(b.gameObject.getLayerType() == LayerTypes.INTERACTABLE){
                   // add score, remove it
                    b.gameObject.setActive(false);
               }else{
                   // static stuff or moving
                   setCollisionSide(b);
               }

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
        this.boxCollider2D.findCollisionSide(otherBox2D);
        Point otherTopRight = otherBox2D.getBounds().getTopRight();
        Point otherBottonLeft = otherBox2D.getBounds().getBottomLeft();
        // switch case for the side hit...
        switch (this.boxCollider2D.getHitSide()){
            case TOP:
                // put this object on the bottom
                this.gameObject.next_position.y = otherBottonLeft.getY() + this.boxCollider2D.getBounds().getHeight() / 2f + spacer;
                velocity.y = gravity;
                break;
            case BOTTOM:
                this.gameObject.next_position.y = otherTopRight.getY() - this.boxCollider2D.getBounds().getHeight() / 2f - spacer;
                velocity.y = 0;
                break;
            case LEFT:
                velocity.x = 0;
                this.gameObject.next_position.x = otherBottonLeft.getX() - this.boxCollider2D.getBounds().getWidth() / 2f - spacer;

                break;
            case RIGHT:
                this.gameObject.next_position.x = otherTopRight.getX() + this.boxCollider2D.getBounds().getWidth() / 2f + spacer;

                break;
        }
    }
}
