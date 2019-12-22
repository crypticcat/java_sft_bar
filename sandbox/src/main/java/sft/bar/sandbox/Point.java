package sft.bar.sandbox;

public class Point {
    public double x, y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance (Point p1) {
        return Math.round(Math.hypot(p1.x - this.x, p1.y - this.y));

    }

}
