package core.game_engine.physics;

public class Rectangle {
    private float x, y, width, height;
    private Point topRight = new Point(1,1);
    private Point bottomLeft = new Point(-1,-1);

    public Rectangle(float x, float y, float w, float h){
        this.width = w;
        this.height = h;
        this.updateBounds( x, y);
    }
    public Point getBottomLeft() {
        return bottomLeft;
    }
    public Point getTopRight() {
        return topRight;
    }
    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }
    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
    public boolean isOverlapping(Rectangle other){
        // check if top bottom not overlapping
        if(this.topRight.getY() > other.bottomLeft.getY() ||
                    this.bottomLeft.getY() < other.topRight.getY()){
            return false;
        }
        //check sides
        if(this.topRight.getX() < other.bottomLeft.getX() ||
                this.bottomLeft.getX() > other.topRight.getX()){
            return false;
        }
        return true;
    }
    public void updateBounds(float x, float y){
        this.x = x;
        this.y = y;
        this.bottomLeft.setX(this.x - this.width / 2f);
        this.bottomLeft.setY(this.y + this.height / 2f);
        this.topRight.setX(this.x + this.width / 2f);
        this.topRight.setY(this.y - this.height / 2f);
    }
}
