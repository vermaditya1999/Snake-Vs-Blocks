import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Game {
    public final static int GRID_ROWS = 8;
    public final static int GRID_COLS = 5;

    public final static double TILE_SIZE = 75;

    public final static double SCREEN_WIDTH = Game.TILE_SIZE * Game.GRID_COLS;
    public final static double SCREEN_HEIGHT = Game.TILE_SIZE * Game.GRID_ROWS;

    public static Window window = Window.Menu;

    // True only if there's a saved game available
    private boolean savedState;

    private Canvas menuCanvas;
    private Canvas gpCanvas;
    private Canvas lbCanvas;

    private Menu menu;
    private Gameplay gameplay;
    private Leaderboard leaderboard;

    public Game(Group root) {
        menuCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        gpCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        lbCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Detect KeyEvents
        gpCanvas.setFocusTraversable(true);

        // Menu canvas, added at the end will be on the top
        root.getChildren().addAll(gpCanvas, lbCanvas, menuCanvas);

        menu = new Menu();
        gameplay = new Gameplay();
        leaderboard = new Leaderboard();
    }

    public void showMenu() {
        menu.showMenu();
    }

    public void showGameplay() {
        gameplay.showGameplay();
    }

    public void showLeaderboard() {
        leaderboard.showLeaderboard();
    }

    private class Menu {

        private GraphicsContext menuGC = menuCanvas.getGraphicsContext2D();

        {
            menuGC.setTextAlign(TextAlignment.CENTER);
            menuGC.setTextBaseline(VPos.CENTER);

            menuCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                double x = event.getX();
                double y = event.getY();

                // Leaderboard button
                if ((x >= TILE_SIZE && x <= 2 * TILE_SIZE) &&
                        (y >= (GRID_ROWS - 3) * TILE_SIZE && y <= (GRID_ROWS - 2) * TILE_SIZE)) {
                    setWindow(Window.Leaderboard);
                }

                // Play Game button
                if ((x >= 3 * TILE_SIZE && x <= 4 * TILE_SIZE) &&
                        (y >= (GRID_ROWS - 3) * TILE_SIZE && y <= (GRID_ROWS - 2) * TILE_SIZE)) {
                    setWindow(Window.Gameplay);
                }
            });
        }

        public void showMenu() {

            // set background
            menuGC.setFill(Color.rgb(32, 32, 32));
            menuGC.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            menuGC.setFill(Color.WHITE);

            menuGC.setFont(new Font(64));
            menuGC.fillText("Snake", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 - 2 * TILE_SIZE);
            menuGC.fillText("Vs", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 - TILE_SIZE - 10);
            menuGC.fillText("Blocks", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 - 20);

            // Leader board
            menuGC.fillRect(TILE_SIZE, (GRID_ROWS - 3) * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            // Play game
            menuGC.fillRect(3 * TILE_SIZE, (GRID_ROWS - 3) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private class Gameplay {
        private GraphicsContext gpGC = gpCanvas.getGraphicsContext2D();

        private double x, y;

        {
            gpCanvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    setWindow(Window.Menu);
                }
            });

            gpCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
                x = event.getX();
                y = event.getY();
            });
        }

        public void showGameplay() {

            // Set background
            gpGC.setFill(Color.rgb(55, 63, 81));
            gpGC.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            // Draw circle
            gpGC.setFill(Color.WHITE);
            gpGC.fillOval(x, y, 25, 25);
        }
    }

    private class Leaderboard {
        private GraphicsContext lbGC = lbCanvas.getGraphicsContext2D();

        {
            lbGC.setTextAlign(TextAlignment.CENTER);
            lbGC.setTextBaseline(VPos.CENTER);
            lbCanvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    setWindow(Window.Menu);
                }
            });
        }

        public void showLeaderboard() {
            lbGC.setFill(Color.BLACK);
            lbGC.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            lbGC.setFill(Color.WHITE);
            lbGC.setFont(new Font(64));
            lbGC.fillText("Leaderboard", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        }
    }

    private void setWindow(Window w) {
        switch (w) {
            case Menu: {
                Game.window = Window.Menu;
                menuCanvas.toFront();
            } break;
            case Gameplay: {
                Game.window = Window.Gameplay;
                gpCanvas.toFront();
            } break;
            case Leaderboard: {
                Game.window = Window.Leaderboard;
                lbCanvas.toFront();
            } break;
        }
    }
}
