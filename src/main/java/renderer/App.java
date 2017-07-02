package renderer;

import javax.swing.*;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Render 3d");
        window.setContentPane(new AppPanel());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
