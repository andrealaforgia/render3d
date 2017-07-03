package renderer;

import java.awt.*;

public class PolygonDrawer {

    private final Graphics2D graphics2D;
    Integer startX = null;
    Integer startY = null;

    Integer prevX = null;
    Integer prevY = null;

    public PolygonDrawer(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public void drawTo(int x, int y) {
        if (prevX == null && prevY == null) {
            startX = prevX = x;
            startY = prevY = y;
            return;
        }
        graphics2D.drawLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
    }

    public void close() {
        graphics2D.drawLine(prevX, prevY, startX, startY);
        prevX = startX = null;
        prevY = startY = null;
    }
}
