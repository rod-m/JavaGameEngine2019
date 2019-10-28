package core.game;

import core.game_engine.GameManager;
import core.game_engine.data_management.DataManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    Player player;
    InputController playerInput;
    DataManager dataManager;
    public TestGame(PApplet p) {
        this.parent = p;
    }

    public void start() {
        dataManager = new DataManager(this.parent);
        dataManager.load();
        game_manager = new GameManager(this.parent);
    if(dataManager.game_data.hasKey("player")){
        // populate player list
        JSONArray playersArray = dataManager.get_json_array("player");
        if(playersArray != null){
            for(int i = 0; i < playersArray.size(); i++){
                JSONObject json = (JSONObject)playersArray.get(i);
                Player newPlayer = new Player(this.parent, json.getInt("x"),json.getInt("y"), 30, 30);
                game_manager.add_game_object(newPlayer);
                player = newPlayer;
            }
        }
    }else{
        player = new Player(this.parent, 300, 100, 20, 20);
        game_manager.add_game_object(player);
        dataManager.save(game_manager.getGame_objects(), "player");
    }
        // add player
//        player = new Player(this.parent, 300, 100, 20, 20);
        playerInput = new InputController(player);
//        game_manager.add_game_object(player);
//
//        gamePlatform = new Platform(this.parent, 150, 300, 200, 80);
//
//        game_manager.add_game_object(gamePlatform);
//
//        CollectableThing collectableThing = new CollectableThing(this.parent, 300, 300, 20, 20);
//        game_manager.add_game_object(collectableThing);

    }

    public void update() {
        playerInput.checkInput();
        game_manager.update();
    }

    public void keyReleased(char key, int keycode) {
        playerInput.keyHandler(key, keycode, false);
    }

    public void keyPressed(char key, int keycode) {
        playerInput.keyHandler(key, keycode, true);
    }
}
