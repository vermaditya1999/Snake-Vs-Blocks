package com.snakevsblocks.window;

import com.snakevsblocks.App;
import javafx.event.Event;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

abstract public class Window {

    protected Canvas canvas;
    protected WindowController windowController;
    protected GraphicsContext gc;

    protected double mouseX;
    protected double mouseY;

    public Window(WindowController wc, Group root) {
        canvas = new Canvas(App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Set the windowController
        windowController = wc;

        // Initialize the GraphicsContext
        gc = canvas.getGraphicsContext2D();

        // Add canvas to the root group
        root.getChildren().add(canvas);

        // Load defaults
        loadDefaults();

        // Add Event Handlers
        addEventHandlers();
    }

    private void loadDefaults() {

        // Enable KeyEvent detection
        canvas.setFocusTraversable(true);

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    public void setMouse(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public void toFront() {
        canvas.toFront();
    }

    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }

    abstract protected void addEventHandlers();

    abstract public void show();
}
