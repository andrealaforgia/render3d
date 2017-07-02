package geometry.xyz;

import java.util.Collection;
import java.util.HashSet;

public class Prism3d {
    private final Point3d centre;
    private final double radius;
    private final double height;
    private final double nVertices;
    private final Collection<Face3d> faces = new HashSet<>();

    public Prism3d(Point3d centre, double radius, double height, double nVertices) {
        this.centre = centre;
        this.radius = radius;
        this.height = height;
        this.nVertices = nVertices;
    }

    public interface Exporter {
        void sendFace(Face3d face3d);
        void sendHeight(double height);
        void export();
    }

    public void project(Exporter exporter) {
        exporter.sendHeight(height);
        if (faces.isEmpty()) {
            initialiseFaces();
        }
        for (Face3d face : faces) {
            exporter.sendFace(face);
        }
        exporter.export();
    }

    private void initialiseFaces() {
        Point3d topCentre = this.centre.newRelative(0, 0, +height/2);
        Point3d btmCentre = this.centre.newRelative(0, 0, -height/2);
        double angle = 0.0;
        Point3d btmPrevVertex = null;
        for (int i=0; i<nVertices; i++, angle += 2*Math.PI/nVertices) {
            Point3d btmCurrVertex = btmCentre.newFromPolarCoordsXY(radius, angle);
            if (i==0) {
                btmPrevVertex = btmCurrVertex;
                continue;
            }
            Point3d topPrevVertex = btmPrevVertex.newRelative(0, 0, +height);
            Point3d topCurrVertex = btmCurrVertex.newRelative(0, 0, +height);
            faces.add(new Face3d(btmCentre, btmPrevVertex, btmCurrVertex));
            faces.add(new Face3d(topCentre, topPrevVertex, topCurrVertex));
            faces.add(new Face3d(btmPrevVertex, btmCurrVertex, topPrevVertex));
            faces.add(new Face3d(btmCurrVertex, topCurrVertex, topPrevVertex));
            btmPrevVertex = btmCurrVertex;
        }
    }
}
