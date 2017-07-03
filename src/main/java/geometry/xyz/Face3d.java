package geometry.xyz;

public class Face3d {
    private Point3d a, b, c;

    public Face3d(Point3d a, Point3d b, Point3d c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public interface Exporter {
        void sendVertexes(Point3d a, Point3d b, Point3d c);
        void export();
    }

    public void export(final Exporter exporter) {
        exporter.sendVertexes(a, b, c);
        exporter.export();
    }
}
