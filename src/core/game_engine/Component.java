package core.game_engine;

public abstract class Component {
    public GameObject gameObject;
    public Component(GameObject gameObject){
         this.gameObject = gameObject;
         this.gameObject.addComponentList(this);
    }
    public abstract void update();
}
