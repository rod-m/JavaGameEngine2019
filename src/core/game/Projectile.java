package core.game;

import core.game_engine.GameManager;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Projectile extends Sprite {
    private PVector direction;
    private float speed = 8f;
    private int lifespan = 20;
    private int life = 0;
    public Projectile(PApplet p, float x, float y, float w, float h) {
        super(p, x, y, w, h);
        layerType = LayerTypes.MOVING;
        boxCollider2D = new BoxCollider2D(this, w, h);
    }
    public void Shoot(PVector d){
        this.direction = d;
    }
    @Override
    public void update(){
        life ++;
        if(life > lifespan){
            // destroy
            return;
        }
        super.update();
        this.position.add(direction.setMag(speed));
        this.checkCollisions();
        this.render();
    }
    private void checkCollisions(){
        if(this.boxCollider2D.otherColliders.size() > 0){
            for(BoxCollider2D b : this.boxCollider2D.otherColliders){

                if(b.gameObject.getLayerType() == LayerTypes.STATIC){
                    // to test any platform can be removed
                    b.gameObject.setActive(false);
                }else{
                    //remove this bullet
                    GameManager.Destroy(this);
                }
            }
            this.boxCollider2D.otherColliders.clear();
        }
    }
    private void render(){
        parent.pushMatrix();
        // bullet shape
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        // possible to rotate the bullet in the direction is is travelling at
        // or keep it round for simplicity
        parent.fill(255, 90, 90, 240);
        this.parent.ellipse(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }
}
