package core.game;
import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    Player player;
    InputController playerInput;
    DataManager dataManager;
    public TestGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent);
        dataManager = new DataManager(this.parent);
        dataManager.load();
        if(dataManager.game_data.hasKey("player")){
            // populate game manager with players
            JSONArray playersArray = dataManager.get_json_array("player");
            if(playersArray != null){
                // create players
                for(int i = 0; i < playersArray.size(); i++){
                    JSONObject playerData = (JSONObject)playersArray.get(i);
                    Player savedPlayer = new Player(this.parent, playerData.getInt("x")
                            ,playerData.getInt("y"), 20, 20);

                    game_manager.add_game_object(savedPlayer);
                    player = savedPlayer;
                }
            }

        }else{
            player = new Player(this.parent, 300,100, 20, 20);
            game_manager.add_game_object(player);
            dataManager.save(game_manager.getGame_objects(), "player");
        }
//        // add player
//
        playerInput = new InputController(player);
//
//
//        gamePlatform = new Platform(this.parent, 150,300, 200, 80);
//
//        game_manager.add_game_object(gamePlatform);
//
//        CollectableThing collectableThing = new CollectableThing(parent, 300, 300, 20, 20);
//        game_manager.add_game_object(collectableThing);

    }
    public void update(){
        playerInput.checkInput();
        game_manager.update();
    }
    public void keyReleased(char key, int keycode){
        playerInput.keyHandler(key, keycode, false);
    }
    public void keyPressed(char key, int keycode){
        playerInput.keyHandler(key, keycode, true);
    }
}
