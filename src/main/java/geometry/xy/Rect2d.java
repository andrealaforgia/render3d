package geometry.xy;

public class Rect2d {
    private double m, c;

    Rect2d(double m, double c) {
        this.m = m;
        this.c = c;
    }

    public Point2d intersectWith(Rect2d other) {
        double x = (other.c - c) / (m - other.m);
        return new Point2d(x, m*x+c);
    }
}
