import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Set title
        primaryStage.setTitle("Snake Vs Blocks");

        // Disable resizing
        primaryStage.setResizable(false);

        // Avoid unnecessary padding on right and bottom
        primaryStage.sizeToScene();

        // Initialize the root Node
        Group root = new Group();

        // Initialize the Scene
        Scene scene = new Scene(root, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Set Scene and show Stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the Game object
        Game game = new Game(root);

        // Run the game
        game.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
