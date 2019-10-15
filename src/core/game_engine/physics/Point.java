package core.game_engine.physics;

public class Point {
    private float x, y;
    public Point(float _x, float _y){
        x = _x;
        y = _y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public String toString(){
        return "Point ["+x+","+y+"]";
    }
}
