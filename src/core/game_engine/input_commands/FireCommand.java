package core.game_engine.input_commands;

public class FireCommand implements Command{
    private MoveAble actor;
    public FireCommand(MoveAble _actor){
        actor = _actor;
    }

    @Override
    public void execute() {
        actor.fire();
    }
}
