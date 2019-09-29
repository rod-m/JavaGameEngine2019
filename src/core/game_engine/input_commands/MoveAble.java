package core.game_engine.input_commands;

/**
 * Any game object which is moveable can impliment this interface
 */
public interface MoveAble {
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();
}
