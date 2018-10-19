import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;
    public static Window window = Window.Menu;

    @Override
    public void start(Stage primaryStage) {

        // Set the window title and disable resizing
        primaryStage.setTitle("Snake vs Blocks");
        primaryStage.setResizable(false);

        // Avoid the unnecessary padding on right and bottom
        primaryStage.sizeToScene();

        // Initialize the root Node
        Group root = new Group();

        // Initialize the Canvas
        Canvas canvas = new Canvas(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // Add Canvas to root Node
        root.getChildren().add(canvas);

        // Initialize the Scene
        Scene scene = new Scene(root);

        // Set Scene and show Stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the animation loop
        animationLoop(canvas.getGraphicsContext2D());
    }

    private void animationLoop(GraphicsContext gc) {

        // Initialize a Game object
        Game game = new Game(gc);

        // Set some global attributes
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        // initialize AnimationTimer
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (Main.window) {
                    case Menu: game.showMenu();
                        break;
                    case Game: // Show game play
                        break;
                    case LeaderBoard: // Show leader board
                        break;
                }
            }
        };

        // Start the animationTimer
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
