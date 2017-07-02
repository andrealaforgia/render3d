package renderer;

import geometry.xyz.Point3d;
import geometry.xyz.Prism3d;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class RunningState extends State {

    Prism3d prism3d;

    public RunningState(AppState appState) throws IOException {
        this.appState = appState;
        init();
    }

    public void init() throws IOException {
        this.prism3d = new Prism3d(new Point3d(-500, -500, 0), 300, 100, 50);
    }

    public void update() throws IOException {
    }

    @Override
    public void draw(final Graphics2D g) {
        g.clearRect(0, 0, 1700, 1000);

        Prism3d.Exporter exporter = new Prism3d.Exporter() {
            public double height;
            public List<Point3d> basePoints;

            @Override
            public void sendBase(List<Point3d> basePoints) {
                this.basePoints = basePoints;
            }

            @Override
            public void sendHeight(double height) {
                this.height = height;
            }

            @Override
            public void export() {
                
            }
        };

//        for (Cell cell : cells) {
//            CellState cellState = cell.getState();
//            double radius = cellState.getRadius();
//            Shape drawnCell = new Ellipse2D.Double(cellState.x, cellState.y, radius, radius);
//            g.setColor(cellState.getColor());
//            g.draw(drawnCell);
//            g.fill(drawnCell);
//        }
    }

    public void keyPressed(int k) {
    }

    public void keyReleased(int k) {
    }
}

