package core.game;
import core.game_engine.GameManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    Player player;
    InputController playerInput;
    public TestGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent);
        gamePlatform = new Platform(this.parent, 30,300, 200, 20);
        gamePlatform.position.x = 0;
        gamePlatform.position.y = 100;
        game_manager.add_game_object(gamePlatform);
        player = new Player(this.parent, 300,200, 20, 20); // add player
        game_manager.add_game_object(player);
        playerInput = new InputController(player); //setup inputs
    }
    public void update(){
        playerInput.checkInput();
        game_manager.update();
    }
    public void keyReleased(char key, int keyCode){
        playerInput.keyHandler(key, keyCode, false);
    }
    public void keyPressed(char key, int keyCode){
        playerInput.keyHandler(key, keyCode, true);
    }
}
