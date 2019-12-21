package sft.bar.sandbox;

public class TaskTwo {

    public static void main(String[] args) {

        Point p1 = new Point(7.0, 5.0);
        Point p2 = new Point(5.0, 7.0);

        System.out.println("Расстояние между точками c координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно: " + distance (p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.round(Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2)));
    }
}
