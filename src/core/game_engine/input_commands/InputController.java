package core.game_engine.input_commands;

import processing.core.PApplet;

public class InputController {
    public InputHandler inputHandler;
    MoveLeftCommand moveLeftCommand;
    MoveRightCommand moveRightCommand;
    boolean left, right;
    public InputController(MoveAble _actor){
        moveLeftCommand = new MoveLeftCommand(_actor);
        moveRightCommand = new MoveRightCommand(_actor);
        inputHandler = new InputHandler(moveLeftCommand, moveRightCommand);
    }
    public void keyHandler(char key, int keycode, boolean active){
        if(key == 'a' || keycode == PApplet.LEFT){
            left = active;
        }else if(key == 'd' || keycode == PApplet.RIGHT){
            right = active;
        }else{
            left = false;
            right = false;
        }
    }
    public void checkInput(){
        if(left){
            inputHandler.moveLeft();
        }else if(right){
            inputHandler.moveRight();
        }
    }
}
