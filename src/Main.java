import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;
    private Group root;

    @Override
    public void start(Stage stage) {

        primaryStage = stage;

        // Disable resizing
        primaryStage.setResizable(false);

        // Avoid unnecessary padding on right and bottom
        primaryStage.sizeToScene();

        // Initialize the root Node
        root = new Group();

        // Initialize the Scene
        scene = new Scene(root, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Set Scene and show Stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the animation loop
        animationLoop();
    }

    private void animationLoop() {

        // Initialize a Game object
        Game game = new Game(root);

        // Initialize animationTimer
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (game.getCurrentWindow()) {
                    case Menu:
                        game.showMenu();
                        break;

                    case Gameplay:
                        game.showGameplay();
                        break;

                    case Leaderboard:
                        game.showLeaderboard();
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
