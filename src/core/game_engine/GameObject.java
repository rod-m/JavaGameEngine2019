package core.game_engine;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class GameObject {
    public PApplet parent;
    public PVector position;
    public PVector next_position;
    public ArrayList<Component> componentList= new ArrayList<>();
    public GameObject(){

    }
    public GameObject(PApplet p){
        this.parent = p;
        this.position = new PVector(0,0,0);
        this.next_position = new PVector(0,0,0);
    }
    public void update(){
        this.position.x += 2;
        parent.rect(this.position.x, this.position.y, 100,50);
    }
    public void addComponentList(Component c){
        componentList.add(c);

    }
}
