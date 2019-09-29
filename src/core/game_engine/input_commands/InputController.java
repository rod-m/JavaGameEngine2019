package core.game_engine.input_commands;
import processing.core.PApplet;
public class InputController {
    public InputHandler inputHandler; // glues movement commands to any input
    MoveLeftCommand moveLeftCommand; // any actor can apply these commands ...
    MoveRightCommand moveRightCommand;
    MoveUpCommand upCommand;
    MoveDownCommand downCommand;
    boolean left, right, up, down; // key handler toggles these true / false
    public InputController(MoveAble actor) {
        moveLeftCommand = new MoveLeftCommand(actor);
        moveRightCommand = new MoveRightCommand(actor);
        upCommand = new MoveUpCommand(actor);
        downCommand = new MoveDownCommand(actor);
        inputHandler = new InputHandler(moveLeftCommand, moveRightCommand, upCommand, downCommand);
    }
    public void keyHandler(char key, int keyCode, boolean active) {
        if (key == 'a' || keyCode == PApplet.LEFT) {
            left = active;
        } else if (key == 'd' || keyCode == PApplet.RIGHT) {
            right = active;
        }else if (key == 'w' || keyCode == PApplet.UP) {
            up = active;
        }else if( key == 's' || keyCode == PApplet.DOWN){
            down = active;
        } else if(!active && keyCode != 0){
            left = false;
            right = false;
            up = false;
            down = false;
        }
    }
    public void checkInput(){
        if (left) {
            inputHandler.moveLeft();
        } else if (right) {
            inputHandler.moveRight();
        }
        if (up) {
            inputHandler.moveUp();
        }else if(down){
            inputHandler.moveDown();
        }
    }
}
