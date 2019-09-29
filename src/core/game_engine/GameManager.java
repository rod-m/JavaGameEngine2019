package core.game_engine;
import processing.core.PApplet;
import java.util.ArrayList;
public class GameManager {
    private PApplet parent;
    private ArrayList<GameObject> game_objects;
    public GameManager(PApplet p){
        this.parent = p;
        startup();
    }
    public void add_game_object(GameObject g){
        // add gameobject to list
        this.game_objects.add(g);
    }
    public void startup(){
        // initialise the list
        this.game_objects = new ArrayList<GameObject>();
    }
    public void update(){
        for(int i = 0; i < this.game_objects.size(); i++){
            GameObject g = this.game_objects.get(i);
            g.update();
        }
    }
}
