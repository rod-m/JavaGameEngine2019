package core.game_engine.input_commands;

public class DragCommand implements Command{
    private MoveAble actor;
    public DragCommand(MoveAble _actor){
        actor = _actor;
    }

    @Override
    public void execute() {
        actor.drag();
    }
}
