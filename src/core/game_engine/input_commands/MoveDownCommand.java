package core.game_engine.input_commands;

public class MoveDownCommand implements Command{
    private MoveAble actor;
    public MoveDownCommand(MoveAble _actor){
        actor = _actor;
    }

    @Override
    public void execute() {
        actor.moveDown();
    }
}
