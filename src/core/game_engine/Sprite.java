package core.game_engine;

import core.game_engine.data_management.Serializable;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;

public class Sprite extends GameObject implements Serializable {
    public BoxCollider2D boxCollider2D;
    public Sprite(PApplet p, float x, float y, float w, float h){
        super(p);
        this.position = new PVector(x, y, 0);
        this.next_position = new PVector(x, y, 0);
    }
    @Override
    public JSONObject serializeToJSON() {
        JSONObject json = new JSONObject();
        json.setInt("x", (int)this.position.x);
        json.setInt("y", (int)this.position.y);
        json.setString("name", "Sprite");
        return json;
    }

    @Override
    public void loadJSONObject(JSONObject json) {

    }
    @Override
    public void update() {
        for(Component c: this.componentList){
            c.update();
        }
    }


}
