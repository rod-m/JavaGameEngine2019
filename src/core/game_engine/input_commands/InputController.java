package core.game_engine.input_commands;

import processing.core.PApplet;

public class InputController {
    public InputHandler inputHandler;
    MoveLeftCommand moveLeftCommand;
    MoveRightCommand moveRightCommand;
    MoveUpCommand moveUpCommand;
    MoveDownCommand moveDownCommand;
    DragCommand dragCommand;
    boolean left, right, up, down;
    public InputController(MoveAble _actor){
        moveLeftCommand = new MoveLeftCommand(_actor);
        moveRightCommand = new MoveRightCommand(_actor);
        moveUpCommand = new MoveUpCommand(_actor);
        moveDownCommand = new MoveDownCommand(_actor);
        dragCommand =  new DragCommand(_actor);
        inputHandler = new InputHandler(moveLeftCommand, moveRightCommand, moveUpCommand, moveDownCommand, dragCommand);
    }
    public void keyHandler(char key, int keycode, boolean active){
        if(key == 'a' || keycode == PApplet.LEFT){
            left = active;
        }else if(key == 'd' || keycode == PApplet.RIGHT){
            right = active;
        }else if(key == 'w' || keycode == PApplet.UP){
            up = active;
        }else if(key == 's' || keycode == PApplet.DOWN){
            down = active;
        }else{
            left = false;
            right = false;
            up = false;
            down = false;
        }
    }
    public void mouseDragged(){
        inputHandler.drag();
    }
    public void checkInput(){
        if(left){
            inputHandler.moveLeft();
        }else if(right){
            inputHandler.moveRight();
        }
        if(up){
            inputHandler.moveUp();
        }else if(down){
            inputHandler.moveDown();
        }
    }
}
