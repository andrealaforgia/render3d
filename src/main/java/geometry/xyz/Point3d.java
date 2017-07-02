package geometry.xyz;

import geometry.xy.Point2d;

public class Point3d {
    double x, y, z;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d newRelative(double deltaX, double deltaY, double deltaZ) {
        return new Point3d(x + deltaX, y + deltaY, z + deltaZ);
    }

    public Point3d newFromPolarCoordsXY(double radius, double angle) {
        return new Point3d(x + radius * Math.cos(angle), y + radius * Math.sin(angle), z);
    }

    public interface Projector {
        void project();
        void sendZ(double z);
        void sendProjectedPoint1(Point2d point2d);
        void sendProjectedPoint2(Point2d point2d);
    }

    public void project(Projector projector) {
        projector.sendZ(z);
        projector.sendProjectedPoint1(new Point2d(x, x));
        projector.sendProjectedPoint2(new Point2d(y, y));
        projector.project();
    }
}
