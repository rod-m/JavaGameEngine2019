package core.game_engine.data_management;

import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class DataManager {
    private PApplet parent;
    private String load_game_file = "game.json";
    private String data_folder = "data_folder/";
    public JSONObject game_data;
    public DataManager(PApplet p){
        parent = p;
    }
    public void load(){
        this.game_data = parent.loadJSONObject(data_folder+load_game_file);
    }
    public void save(ArrayList<Sprite> json_list, String nameOfList){
        JSONArray new_list = new JSONArray();
        for(Serializable serialJSON : json_list){

            new_list.append(serialJSON.serializeToJSON());
        }
        this.game_data.setJSONArray(nameOfList, new_list);
        parent.saveJSONObject(this.game_data, data_folder+ load_game_file);

    }
    public JSONArray get_json_array(String name){
        if(this.game_data.hasKey(name)){
            return this.game_data.getJSONArray(name);
        }
        return null;
    }
}
