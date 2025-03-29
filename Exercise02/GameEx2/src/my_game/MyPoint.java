package my_game;

/**
 * MyPoint class
 * 
 * @implNote This class implements the methods and data that represents a point
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class MyPoint {

    private int x   = 0;
    private int y   = 0;

    public MyPoint() {
        this.set(0, 0);
    }

    public MyPoint(int x, int y) {
        this.set(x, y);
    }

    public void set(int x, int y) {
        this.x  = x;
        this.y  = y;
    }

    public void set(MyPoint myPoint) {
        this.x  = myPoint.getX();
        this.y  = myPoint.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public MyPoint get() {
        return this;
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

    public double getDistance(int x, int y) {
        return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
    }

    public double getDistance(MyPoint myPoint) {
        return this.getDistance(myPoint.getX(), myPoint.getY());
    }
}