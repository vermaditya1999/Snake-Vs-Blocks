import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;

    @Override
    public void start(Stage primaryStage) {

        // set the window title and disable resizing
        primaryStage.setTitle("Snake vs Blocks");
        primaryStage.setResizable(false);

        // avoid the unnecessary padding on right and bottom
        primaryStage.sizeToScene();

        // initialize the root node
        Group root = new Group();

        // initialize the canvas
        Canvas canvas = new Canvas(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // add canvas to root node
        root.getChildren().add(canvas);

        // initialize the scene
        Scene scene = new Scene(root);

        // set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // start the animation loop
        animationLoop(canvas.getGraphicsContext2D());
    }

    private void animationLoop(GraphicsContext gc) {

        // initialize a Game object
        Game game = new Game(gc);

        // set some global attributes
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setStroke(Color.WHITE);

        // initialize AnimationTimer
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };

        // star the animation timer
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
