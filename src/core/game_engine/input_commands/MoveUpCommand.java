package core.game_engine.input_commands;

public class MoveUpCommand implements Command{
    private MoveAble actor;
    public MoveUpCommand(MoveAble _actor){
        actor = _actor;
    }

    @Override
    public void execute() {
        actor.moveUp();
    }
}
