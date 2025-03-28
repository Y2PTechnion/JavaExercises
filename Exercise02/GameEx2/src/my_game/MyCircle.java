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

    private MyPoint centerPoint = new MyPoint();
    private int radius;

    public MyCircle(int x, int y, int radius) {
        this.set(x, y, radius);
    }

    public MyCircle(MyPoint centerPoint, int radius) {
        this.set(centerPoint, radius);
    }

    public void set(int x, int y, int radius) {
        this.centerPoint.set(x, y);
        this.radius = radius;
    }

    public void set(MyPoint centerPoint, int radius) {
        this.centerPoint.set(centerPoint);
        this.radius = radius;
    }

    public void set(MyCircle circle) {
        this.set(circle.getCenter(), circle.getRadius());
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