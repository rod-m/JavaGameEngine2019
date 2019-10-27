package core.game_engine;

import core.game_engine.data_management.Serializable;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;

public class Sprite extends GameObject implements Serializable {
    public BoxCollider2D boxCollider2D;
    public String name = "Sprite";
    public Sprite(PApplet p, float x, float y, float w, float h){
        super(p);
        this.position = new PVector(x, y, 0);
        this.next_position = new PVector(x, y, 0);
    }

    @Override
    public void update() {
        for(Component c: this.componentList){
            c.update();
        }
    }

    @Override
    public JSONObject serializeToJSON() {
        JSONObject sprite_data = new JSONObject();
        sprite_data.setInt("x", (int)this.position.x);
        sprite_data.setInt("y", (int)this.position.y);
        sprite_data.setString("name", this.name);
        sprite_data.setInt("layerType", this.layerType.ordinal());
        return sprite_data;
    }

    @Override
    public void loadJSONObject(JSONObject json) {
        this.name = json.getString("name");
        this.position.x = (float)json.getInt("x");
        this.position.y = (float)json.getInt("y");
        //this.layerType = LayerTypes.valueOf()
    }
}
