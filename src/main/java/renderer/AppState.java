package renderer;

import java.io.IOException;

public class AppState {

    private final int width;
    private final int height;

    private State[] states;
    private int currentState;

    public static final int NUM_STATES = 1;
    public static final int RUNNING_STATE = 0;

    public AppState(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.states = new State[NUM_STATES];
        this.currentState = RUNNING_STATE;
        loadState(currentState);
    }

    private void loadState(int state) throws IOException {
        if (state == RUNNING_STATE)
            states[state] = new RunningState(this, width, height);
    }

    private void unloadState(int state) {
        states[state] = null;
    }

    public void setState(int state) throws IOException {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void update() {
        try {
            states[currentState].update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g) {
        try {
            states[currentState].draw(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(int k) {
        states[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        states[currentState].keyReleased(k);
    }
}
