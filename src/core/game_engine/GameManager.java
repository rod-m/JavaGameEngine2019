package core.game_engine;
import processing.core.PApplet;
import java.util.ArrayList;
public class GameManager {
    private PApplet parent;
    private ArrayList<Sprite> game_objects;
    private static  GameManager Instance;
    public GameManager(PApplet p){
        this.parent = p;
        Instance = this;
        startup();
    }
    public static void Instantiate(Sprite g){
        Instance.game_objects.add(0, g);
    }
    public static void Destroy(Sprite g){
        Instance.game_objects.remove(g);
        g = null;
    }

    public void add_game_object(Sprite g){
        // add gameobject to list
        this.game_objects.add(g);
    }

    public ArrayList<Sprite> getGame_objects() {
        return game_objects;
    }
    public void add_sprite_array(ArrayList<Sprite> newSprites){
        this.game_objects.addAll(newSprites);
    }
    public void startup(){
        // initialise the list
        this.game_objects = new ArrayList<Sprite>();
    }

    public void update(){
        for(int i = 0; i < this.game_objects.size(); i++){
            Sprite gA = this.game_objects.get(i);
            if(!gA.isActive) continue;
            gA.update();
            for(int j = i + 1; j <this.game_objects.size(); j++){
                Sprite gB = this.game_objects.get(j);
                if(!gB.isActive) continue;
                // if gA is static don't check collisions.
                // CHECk that MOVING objects are added to game_objects before other types
                if(gA.getLayerType() == LayerTypes.MOVING
                        && gB.getLayerType() != LayerTypes.BACKGROUND
                        && gB.getLayerType() != LayerTypes.MOVING){
                    gA.boxCollider2D.check_collisions(gB.boxCollider2D);
                }

            }
        }
    }

}
