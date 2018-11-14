package com.snakevsblocks.window;

import javafx.event.Event;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

abstract public class Window {

    protected Canvas canvas;
    protected WindowController windowController;
    protected GraphicsContext gc;

    protected double mouseX;
    protected double mouseY;

    public Window(WindowController wc, Canvas canvas) {

        // Set the windowController
        windowController = wc;

        // Set canvas
        this.canvas = canvas;

        // Initialize the GraphicsContext
        gc = canvas.getGraphicsContext2D();

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
