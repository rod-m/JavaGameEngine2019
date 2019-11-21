package core.game;

import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import java.util.ArrayList;

public class LevelManager {
    private PApplet parent;
    private GameManager gameManager;
    char currentKey = 'p';
    private String level_name = "Level 1";
    private int platformWidth = 50;
    private int platformHeight = 20;
    String itemType = "Platform";
    boolean mouse_down = false;
    DataManager dataManager;
    ArrayList<Sprite> loaded_game_objects;
    public float screenMove = 10f; // amount to move the virtual camera
    public LevelManager(PApplet p, GameManager g){

        parent = p;
        gameManager = g;
        dataManager = new DataManager(this.parent);
        dataManager.load();
    }

    public void load_level(String _level_name){
        level_name = _level_name;
        load_object_list(level_name);
        this.gameManager.add_sprite_array(loaded_game_objects);

    }
    public ArrayList<Sprite> load_players(){
        load_object_list("player");
        if(loaded_game_objects.size() == 0){
            Player player = new Player(this.parent, 300,100, 20, 20);
            loaded_game_objects.add(player);
            this.gameManager.add_sprite_array(loaded_game_objects);
            dataManager.save(gameManager.getGame_objects(), "player");
        }else{
            this.gameManager.add_sprite_array(loaded_game_objects);
        }
        return loaded_game_objects;
    }
    private void load_object_list(String list_name){
        loaded_game_objects = new ArrayList<>();
        if(dataManager.game_data != null && dataManager.game_data.hasKey(list_name)){
            JSONArray levelItemsArray = dataManager.game_data.getJSONArray(list_name);
            for(int i = 0; i < levelItemsArray.size(); i++){
                JSONObject itemData = (JSONObject) levelItemsArray.get(i);
                itemType = itemData.getString("type");
                add_object(itemData.getInt("x")
                        ,itemData.getInt("y")
                        ,itemData.getInt("w")
                        ,itemData.getInt("h"));
            }
        }
    }
    private void save_level(){
        dataManager.save(gameManager.getGame_objects(), level_name);
    }
    public void update(){
        if(parent.keyPressed){
            if(currentKey != parent.key){
                currentKey = parent.key;
                handle_key();
            }
        }
        if(parent.mousePressed){
            if(!mouse_down){
                mouse_down = true;
                handle_key();
            }
        }else{
            mouse_down = false;
        }
        show_menu();
    }
    private void show_menu(){
        parent.pushMatrix();
        if(parent.mouseY < 20 && parent.mouseX < 55){
            parent.rectMode(PApplet.CORNERS);
            parent.fill(120, 240);
            parent.rect(0,0, parent.width, 25);
            parent.fill(250);
            parent.textSize(13);
            parent.text("Edit mode: KEYS - Exit 1 | Add Platform P | Delete D | Add Collectable C | Save S", 5, 15);

        }else{
            parent.fill(0,255, 0, 250);
            parent.rect(0,0, 30, 30);
            parent.textSize(13);
            parent.fill(0);
            parent.text("?", 5, 11);

        }

        parent.popMatrix();
    }

    private void handle_key(){
        switch (currentKey){
            case 'p':
            case 'P':
                System.out.println("Add Platform");
                Sprite new_sprite = add_object(grid_placement(parent.mouseX, platformWidth)
                                             , grid_placement(parent.mouseY, platformHeight)
                                             , platformWidth, platformHeight);
                gameManager.add_game_object(new_sprite);
                break;
            case 'C':
            case 'c':
                System.out.println("Add Collectable");
                break;
            case 'S':
            case 's':
                System.out.println("Save All");
                save_level();
                break;
            case 'D':
            case 'd':
                System.out.println("Delete Platform");
                remove_object();
                break;
            case '.':
            case '>':
                screenMove += 5f;
                currentKey = '0';
                break;
            case ',':
            case '<':
                screenMove -= 5f;
                currentKey = '0';
                break;

        }
        switch(parent.keyCode){
            case PApplet.UP:
                GameManager.MOVE_CAMERA(0f, -screenMove);
                currentKey = '0';
                break;
            case PApplet.DOWN:
                GameManager.MOVE_CAMERA(0f, screenMove);
                currentKey = '0';
                break;
            case PApplet.LEFT:
                GameManager.MOVE_CAMERA(-screenMove, 0f);
                currentKey = '0';
                break;
            case PApplet.RIGHT:
                GameManager.MOVE_CAMERA(screenMove, 0f);
                currentKey = '0';
                break;
        }
    }
    private void remove_object(){
        // go thru game objects and delete
        for(int i = 0; i < gameManager.getGame_objects().size(); i++){
            Sprite gA = gameManager.getGame_objects().get(i);
            if(gA.boxCollider2D.mouse_over){
                gameManager.getGame_objects().remove(i);
            }
        }
    }
    private Sprite add_object(int x, int y, int w, int h){
        // adjust platform placement as the mouseX, mouseY is relative to the screen view
        // the camera transpose x,y needs to be subtracted to correct the virtual position
        x -= GameManager.CAMERA_POSITION().x;
        y -= GameManager.CAMERA_POSITION().y;

        Sprite sprite = null;
        switch (itemType){
            case "Platform":
                Platform gamePlatform = new Platform(this.parent, x, y,w,h);
                //gameManager.add_game_object(gamePlatform);
                loaded_game_objects.add(gamePlatform);
                sprite = gamePlatform;
                break;
            case "Collectable":

                break;
            case "Player":
                // add player
                Player player = new Player(this.parent, x, y, w, h);
                loaded_game_objects.add(player);
                sprite = player;
                break;
        }

        return sprite;
    }
    private int grid_placement(int num, int sizeOfGrid)
    {
        int grid = sizeOfGrid * Math.floorDiv(num, sizeOfGrid) + sizeOfGrid / 2;
        return grid;
    }
}
