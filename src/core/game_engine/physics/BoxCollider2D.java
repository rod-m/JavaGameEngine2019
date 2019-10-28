package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;

import java.util.ArrayList;

public class BoxCollider2D extends Component {
    // bounds rectangle
    public Rectangle bounds;
    public SIDES hitSide = SIDES.NONE;
    private boolean hasCollided = false;
    public ArrayList<BoxCollider2D> otherColliders = new ArrayList<>();
    public BoxCollider2D(Sprite g, float w, float h){
        super(g);
        this.bounds = new Rectangle(gameObject.position.x, gameObject.position.y, w, h);
    }
    @Override
    protected void update() {
        this.bounds.updateBounds(gameObject.position.x, gameObject.position.y);
    }
    public void check_collisions(BoxCollider2D other){
        hasCollided = bounds.isOverLapping(other.bounds);
        if(hasCollided){
            this.otherColliders.add(other);
        }
    }
    public void findCollisionSide(BoxCollider2D otherBox2D){
        hitSide = SIDES.NONE;
        boolean isTouchingAbove = this.bounds.getIsTouchingAbove(otherBox2D.bounds);
        boolean isTouchingBelow = false;
        if(!isTouchingAbove){
            isTouchingBelow = this.bounds.getIsTouchingBelow(otherBox2D.bounds);

        }
        if(isTouchingAbove){
            hitSide = SIDES.BOTTOM;
        }else if(isTouchingBelow){
            hitSide = SIDES.TOP;
        }
        if(hitSide == SIDES.NONE){
            boolean isTouchingRight = this.bounds.getIsTouchingRight(otherBox2D.bounds);
            boolean isTouchingLeft = false;
            if(!isTouchingRight){
                 isTouchingLeft = this.bounds.getIsTouchingLeft(otherBox2D.bounds);
            }
            if(isTouchingLeft){
                hitSide = SIDES.LEFT;
            }else if(isTouchingRight){
                hitSide = SIDES.RIGHT;
            }
        }
    }
}
