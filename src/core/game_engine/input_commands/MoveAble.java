package core.game_engine.input_commands;

public interface MoveAble {
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void drag();
}
