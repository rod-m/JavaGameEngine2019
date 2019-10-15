package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;

import java.util.ArrayList;

public class BoxCollider2D extends Component {
    // bounds rectangle
    public Rectangle bounds;
    private boolean hasCollided = false;
    public ArrayList<BoxCollider2D> otherColliders = new ArrayList<>();
    public BoxCollider2D(Sprite g, float w, float h){
        super(g);
        bounds = new Rectangle(gameObject.position.x, gameObject.position.y, w, h);
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
}
