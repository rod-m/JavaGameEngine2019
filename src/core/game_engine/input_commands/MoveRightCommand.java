package core.game_engine.input_commands;

public class MoveRightCommand implements Command {
    private MoveAble actor;
    public MoveRightCommand( MoveAble _actor){
        actor = _actor;
    }
    @Override
    public void execute() {
        actor.moveRight();
    }
}
