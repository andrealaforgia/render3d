package renderer;

import geometry.xy.Point2d;
import geometry.xy.Rect2d;
import geometry.xyz.Face3d;
import geometry.xyz.Point3d;
import geometry.xyz.Prism3d;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RunningState extends State {

    private final int width;
    private final int height;

    Prism3d prism3d;
    Prism3d prism3d_2;
    Prism3d prism3d_3;

    public RunningState(AppState appState, int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.appState = appState;
        init();
    }

    public void init() throws IOException {
        this.prism3d = new Prism3d(new Point3d(0, 0, 0), 400, 200, 5);
        this.prism3d_2 = new Prism3d(new Point3d(0, 450, 0), 400, 200, 5);
        this.prism3d_3 = new Prism3d(new Point3d(0, -450, 0), 400, 200, 5);
    }

    public void update() throws IOException {
    }

    @Override
    public void draw(final Graphics2D g) {
        g.clearRect(0, 0, width, height);

        Prism3d.Exporter exporter = new Prism3d.Exporter() {
            double height;
            Collection<Face3d> faces = new HashSet<>();

            public void sendFace(Face3d face) {
                faces.add(face);
            }

            public void sendHeight(double height) {
                this.height = height;
            }

            public void export() {
                final Point2d origin = new Point2d(0, 0);
                final Point2d fpLeft = new Point2d(0, 0);
                final Point2d fpRight = new Point2d(width - 1, 0);

                final PolygonDrawer polygonDrawer = new PolygonDrawer(g);

                for (Face3d face3d : faces) {
                    Face3d.Exporter exporter = new Face3d.Exporter() {
                        Collection<Point2d> vertices = new ArrayList<>();

                        @Override
                        public void sendVertexes(Point3d a, Point3d b, Point3d c) {
                            Point3d.Projector projector = new Point3d.Projector() {
                                Point2d left;
                                Point2d right;
                                double z;

                                @Override
                                public void project() {
                                    Rect2d r1 = left.rectThrough(fpRight);
                                    Rect2d r2 = right.rectThrough(fpLeft);
                                    vertices.add(r1.intersectWith(r2));
                                }

                                @Override
                                public void sendZ(double z) {
                                    this.z = z;
                                }

                                @Override
                                public void sendProjectedPoint1(Point2d point2d) {
                                    double d = point2d.distanceFrom(origin);
                                    left = new Point2d(width / 2 - d, height - 1 - z);
                                }

                                @Override
                                public void sendProjectedPoint2(Point2d point2d) {
                                    double d = point2d.distanceFrom(origin);
                                    right = new Point2d(width / 2 + d, height - 1 - z);
                                }
                            };
                            a.project(projector);
                            b.project(projector);
                            c.project(projector);
                        }

                        @Override
                        public void export() {
                            for (Point2d vertex : vertices) {
                                vertex.export(new Point2d.Exporter() {
                                    @Override
                                    public void export(int x, int y) {
                                        polygonDrawer.drawTo(x, y);
                                    }
                                });
                            }
                            polygonDrawer.close();
                        }
                    };
                    face3d.export(exporter);
                }
            }
        };

        //prism3d.export(exporter);
        prism3d_2.export(exporter);
        //prism3d_3.export(exporter);
    }

    public void keyPressed(int k) {
    }

    public void keyReleased(int k) {
    }
}

