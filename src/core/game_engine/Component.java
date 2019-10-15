package core.game_engine;

public abstract class Component {
    public GameObject gameObject;
    public Component(GameObject g){
        this.gameObject = g;
        //add to componentlist
        this.gameObject.addComponentList(this);
    }
    protected abstract void update();
}
