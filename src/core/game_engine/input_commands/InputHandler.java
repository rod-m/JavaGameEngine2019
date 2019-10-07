package core.game_engine.input_commands;

public class InputHandler implements MoveAble {
    private Command leftCommand, rightCommand;
    public InputHandler(Command left, Command right){
        leftCommand = left;
        rightCommand = right;
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

    }

    @Override
    public void moveDown() {

    }
}
