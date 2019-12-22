package sft.bar.sandbox;

public class TaskTwo {

    public static void main(String[] args) {

        Point p1 = new Point(7.0, 5.0);
        Point p2 = new Point(5.0, 7.0);

        System.out.println("Расстояние между точками c координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно: " + p1.distance(p2));
    }
}
