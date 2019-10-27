package core.game_engine.data_management;

import processing.data.JSONObject;

public interface Serializable {
    public JSONObject serializeToJSON();
    public void loadJSONObject(JSONObject json);
}
