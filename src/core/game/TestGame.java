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

        // add player
        player = new Player(this.parent, 300,200, 20, 20);
        playerInput = new InputController(player);
        game_manager.add_game_object(player);
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
