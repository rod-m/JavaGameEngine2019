package core.game_engine.physics;

public class Rectangle {
    private float x, y, width, height;
    private Point topRight = new Point(1,1);
    private Point bottomLeft = new Point(-1,-1);
    public Rectangle(float _x, float _y, float w, float h){
        width = w;
        height = h;
        this.updateBounds(_x,_y);
    }
    public void updateBounds(float _x, float _y){
        x = _x;
        y = _y;
        bottomLeft.setX(x - width / 2f);
        bottomLeft.setY(y + height / 2f);
        topRight.setX(x + width / 2f);
        topRight.setY(y - height / 2f);
     }
     public boolean isOverLapping(Rectangle other){
        // if is not overrlapping return false
         // is it above
         if(topRight.getY() > other.bottomLeft.getY() || bottomLeft.getY() < other.topRight.getY()){
             return false;
         }
         // left
         if(topRight.getX() < other.bottomLeft.getX() || bottomLeft.getX() > other.topRight.getX()){
             return false;
         }
        return true;
     }
}
