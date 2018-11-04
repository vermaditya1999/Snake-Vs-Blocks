import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

abstract public class Window extends Canvas {

    protected WindowController windowController;
    protected GraphicsContext gc;

    protected double mouseX;
    protected double mouseY;

    public Window(WindowController wc, Group root) {
        super(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Set the windowController
        windowController = wc;

        // Initialize the GraphicsContext
        gc = getGraphicsContext2D();

        // Add canvas to the root group
        root.getChildren().add(this);

        // Load defaults
        loadDefaults();

        // Add Event Handlers
        addEventHandlers();
    }

    private void loadDefaults() {

        // Enable KeyEvent detection
        setFocusTraversable(true);

        // Set Text Align and Baseline to CENTER
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    protected void resetMouseVars() {
        mouseX = Game.SCREEN_WIDTH / 2;
        mouseY = Game.SCREEN_HEIGHT / 2;
    }

    abstract protected void addEventHandlers();

    abstract protected void show();
}
