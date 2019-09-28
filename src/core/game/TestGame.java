package core.game;
import core.game_engine.GameManager;
import core.game_engine.GameObject;
import processing.core.PApplet;
public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    Player player;
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
        game_manager.add_game_object(player);
    }
    public void update(){
        game_manager.update();
    }
}
