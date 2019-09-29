package core.game_engine.input_commands;

import core.game_engine.input_commands.Command;
/*
* Objective is to develop an input handler that can control any moveable object
* */
public class InputHandler {
    private Command leftCommand, rightCommand, upCommand, downCommand;
    public InputHandler(Command left, Command right, Command up, Command down){
        leftCommand = left;
        rightCommand = right;
        upCommand = up;
        downCommand = down;
    }
    public void moveLeft(){
        leftCommand.execute();
    }
    public void moveRight(){
        rightCommand.execute();
    }
    public void moveUp(){
        upCommand.execute();
    }
    public void moveDown(){
        downCommand.execute();
    }
}
