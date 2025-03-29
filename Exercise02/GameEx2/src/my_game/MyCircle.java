package my_game;

/**
 * MyCircle class
 * 
 * @implNote This class implements the methods and data that represents a circle
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class MyCircle {

    private MyPoint centerPoint = null;
    private int radius          = 0;

    public MyCircle(int x, int y, int radius) {
        this.centerPoint    = new MyPoint(x, y);
        this.setRadius(radius);
    }

    public MyCircle(MyPoint centerPoint, int radius) {   
        this.centerPoint    = new MyPoint(centerPoint.getX(), centerPoint.getY());        
        this.setRadius(radius);
    }

    public void set(int x, int y, int radius) {
        this.setCenter(x, y);
        this.setRadius(radius);
    }

    public void set(MyPoint centerPoint, int radius) {
        this.setCenter(centerPoint);
        this.setRadius(radius);
    }

    public void set(MyCircle circle) {
        this.set(circle.getCenter(), circle.getRadius());
    }

    public void setCenter(int x, int y) {
        this.centerPoint.set(x, y);
    }

    public void setCenter(MyPoint centerPoint) {
        this.centerPoint.set(centerPoint);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public MyPoint getCenter() {
        return this.centerPoint;
    }

    public int getRadius() {
        return this.radius;
    }

    public MyCircle get() {
        return this;
    }

    public String toString() {
        return "[" + this.centerPoint + ", " + this.getRadius() + "]";
    }

    public boolean isPointInsideCircle(MyPoint point) {
        double  distanceOfPointToCenterOfCircle = point.getDistance(this.getCenter());
        boolean pointIsInsideCircle             = distanceOfPointToCenterOfCircle <= this.getRadius();

        return  pointIsInsideCircle;
    }

    public boolean intersects(MyCircle otherCircle) {
        double  distanceBetweenTheCentersOfBothCircles  = otherCircle.getCenter().getDistance(this.getCenter());
        double  sumOfRadiusOfBothCircles                = this.getRadius() + otherCircle.getRadius();
        boolean bothCirclesIntersects                   = distanceBetweenTheCentersOfBothCircles < sumOfRadiusOfBothCircles;

        return  bothCirclesIntersects;
    }
}