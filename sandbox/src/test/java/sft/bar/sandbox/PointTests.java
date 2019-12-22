package sft.bar.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1 () {
        Point p1 = new Point(8.7, 8.7);
        Point p2 = new Point(5.5, 5.5);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistance2 () {
        Point p1 = new Point(0.1, 0.1);
        Point p2 = new Point(0.1, 0.1);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistance3 () {
        Point p1 = new Point(7.0, 5.0);
        Point p2 = new Point(5.0, 7.0);

        Assert.assertEquals(p1.distance(p2), 3.0);
    }
}
