package core.game_engine;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
public class GameManager {
    private PApplet parent;
    private ArrayList<Sprite> game_objects;
    private static  GameManager Instance;

    private PVector cameraTranspose = new PVector(0,0,0);
    private PVector screenCentre = new PVector(0,0,0);
    public static PVector CAMERA_OFFSET = new PVector(0, 50f);
    public static float CAMERA_DISTANCE = 150f;
    public static void MOVE_CAMERA(float x, float y){
        System.out.println(String.format("Move camera %f,%f", x,y) );
        Instance.cameraTranspose.x += x;
        Instance.cameraTranspose.y += y;

    }
    public static PVector CAMERA_POSITION(){
        return Instance.cameraTranspose.copy();
    }
    public static PVector SCREEN_CENTRE(){
        Instance.screenCentre.x = (Instance.parent.width / 2f) + Instance.cameraTranspose.x;
        Instance.screenCentre.y = (Instance.parent.height / 2f) + Instance.cameraTranspose.y;
        return Instance.screenCentre;
    }
    public static void CAMERA_FOLLOW(PVector target){

        if(PVector.dist(target, GameManager.SCREEN_CENTRE()) > GameManager.CAMERA_DISTANCE){
            PVector cameraLerp = GameManager.SCREEN_CENTRE().copy();
            // camera is moved based on relative distance from target to screenCentre
            // the cameraLerp is the vector from the target to the screen center,
            // with an offset added
            cameraLerp.sub(target).sub(Instance.cameraTranspose).add(GameManager.CAMERA_OFFSET);
            // 0.1f is the amount movement per frame based on 1.0f being moving 100%
            // so in 10 fames the camera moves to the target
            Instance.cameraTranspose.lerp(cameraLerp, 0.1f);
        }

    }
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
            // Camera transposed to allow for a follow camera effect
            parent.pushMatrix();
                parent.translate(this.cameraTranspose.x, this.cameraTranspose.y);
                gA.update();
            parent.popMatrix();

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
