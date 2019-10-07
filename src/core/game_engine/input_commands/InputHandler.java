package core.game_engine.input_commands;

public class InputHandler implements MoveAble {
    private Command leftCommand, rightCommand, upCommand, downCommand;
    public InputHandler(Command left, Command right, Command up, Command down){
        leftCommand = left;
        rightCommand = right;
        upCommand = up;
        downCommand = down;
    }

    @Override
    public void moveLeft() {
        leftCommand.execute();
    }

    @Override
    public void moveRight() {
        rightCommand.execute();
    }

    @Override
    public void moveUp() {
        upCommand.execute();
    }

    @Override
    public void moveDown() {
        downCommand.execute();
    }
}
