package com.snakevsblocks.window;

import javafx.event.Event;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

import java.io.Serializable;

/**
 * Abstract Window class used to create Windows.
 */
abstract public class Window implements Serializable {

    /**
     * Canvas for the window.
     */
    protected transient Canvas canvas;

    /**
     * Window Controller for the window.
     *
     * @see WindowController
     */
    protected transient WindowController windowController;

    /**
     * The graphics context of the canvas.
     */
    protected transient GraphicsContext gc;

    /**
     * x position of the mouse.
     */
    protected double mouseX;

    /**
     * y position of the mouse.
     */
    protected double mouseY;

    /**
     * Creates a new Window
     * @param windowController The window controller for the window.
     * @param canvas The canvas for the window.
     */
    public Window(WindowController windowController, Canvas canvas) {
        init(windowController, canvas);
    }

    /**
     * Initializes the window.
     * @param windowController The window controller for the window.
     * @param canvas The canvas for the window.
     */
    public void init(WindowController windowController, Canvas canvas) {
        this.windowController = windowController;
        this.canvas = canvas;

        gc = canvas.getGraphicsContext2D();

        loadDefaults();
        addEventHandlers();
    }

    /**
     * Loads the deafult properties for the window.
     */
    private void loadDefaults() {

        // Enable KeyEvent detection
        canvas.setFocusTraversable(true);

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    /**
     * Set the mouse variables of the window.
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     */
    public void setMouse(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    /**
     * Bring the windows' canvas to front.
     */
    public void toFront() {
        canvas.toFront();
    }

    /**
     * Fire a event for the window.
     * @param event The event to be fired.
     */
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }

    /**
     * In this method all the event handlers must be added to the canvas of the window.
     */
    abstract protected void addEventHandlers();

    /**
     * This methods should contain the code for showing the window.
     */
    abstract public void show();
}
