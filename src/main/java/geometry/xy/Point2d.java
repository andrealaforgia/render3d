package geometry.xy;

public class Point2d {
    private double x, y;

    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rect2d rectThrough(Point2d other) {
        double m = (other.y - y) / (other.x - x);
        double c = y - m*x;
        return new Rect2d(m, c);
    }
}
