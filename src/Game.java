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

    public static Window window = Window.Menu;

    // True only if there's a saved game available
    private boolean savedState;

    private Canvas menuCanvas;
    private Canvas gameplayCanvas;
    private Canvas leaderboardCanvas;

    private Menu menu;
    private Gameplay gameplay;
    private Leaderboard leaderboard;

    public Game(Group root) {
        menuCanvas = new Canvas(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        gameplayCanvas = new Canvas(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        leaderboardCanvas = new Canvas(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        gameplayCanvas.setFocusTraversable(true);

        // Menu canvas, added at the end will be on the top
        root.getChildren().addAll(gameplayCanvas, leaderboardCanvas, menuCanvas);

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
        private double width = Main.SCREEN_WIDTH;
        private double height = Main.SCREEN_HEIGHT;
        private double tile = Game.TILE_SIZE;
        private GraphicsContext menuGC = menuCanvas.getGraphicsContext2D();

        {
            menuGC.setTextAlign(TextAlignment.CENTER);
            menuGC.setTextBaseline(VPos.CENTER);
            menuCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                double x = event.getX();
                double y = event.getY();

                // Leaderboard button
                if ((x >= tile && x <= 2 * tile) &&
                        (y >= (GRID_ROWS - 3) * tile && y <= (GRID_ROWS - 1) * tile)) {
                    setWindow(Window.Leaderboard);
                }

                // Play Game button
                if ((x >= 3 * tile && x <= 4 * tile) &&
                        (y >= (GRID_ROWS - 3) * tile && y <= (GRID_ROWS - 1) * tile)) {
                    setWindow(Window.Gameplay);
                }
            });
        }

        public void showMenu() {

            // set background
            menuGC.setFill(Color.rgb(32, 32, 32));
            menuGC.fillRect(0, 0, width, height);

            menuGC.setFill(Color.WHITE);

            menuGC.setFont(new Font(64));
            menuGC.fillText("Snake", width / 2, height / 2 - 2 * tile);
            menuGC.fillText("Vs", width / 2, height / 2 - tile - 10);
            menuGC.fillText("Blocks", width / 2, height / 2 - 20);

            // Leader board
            menuGC.fillRect(tile, (GRID_ROWS - 3) * tile, tile, tile);

            // Play game
            menuGC.fillRect(3 * tile, (GRID_ROWS - 3) * tile, tile, tile);
        }
    }

    private class Gameplay {
        private double width = Main.SCREEN_WIDTH;
        private double height = Main.SCREEN_HEIGHT;
        private double tile = Game.TILE_SIZE;
        private GraphicsContext gpGC = gameplayCanvas.getGraphicsContext2D();

        {
            gpGC.setTextAlign(TextAlignment.CENTER);
            gpGC.setTextBaseline(VPos.CENTER);
            gameplayCanvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    setWindow(Window.Menu);
                }
            });
        }

        public void showGameplay() {
            gpGC.setFill(Color.BLACK);
            gpGC.fillRect(0, 0, width, height);

            gpGC.setFill(Color.WHITE);
            gpGC.setFont(new Font(64));
            gpGC.fillText("GamePlay", width / 2, height / 2);
        }
    }

    private class Leaderboard {
        private double width = Main.SCREEN_WIDTH;
        private double height = Main.SCREEN_HEIGHT;
        private double tile = Game.TILE_SIZE;
        private GraphicsContext lbGC = leaderboardCanvas.getGraphicsContext2D();

        {
            lbGC.setTextAlign(TextAlignment.CENTER);
            lbGC.setTextBaseline(VPos.CENTER);
            leaderboardCanvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    setWindow(Window.Menu);
                }
            });
        }

        public void showLeaderboard() {
            lbGC.setFill(Color.BLACK);
            lbGC.fillRect(0, 0, width, height);

            lbGC.setFill(Color.WHITE);
            lbGC.setFont(new Font(64));
            lbGC.fillText("Leaderboard", width / 2, height / 2);
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
                gameplayCanvas.toFront();
            } break;
            case Leaderboard: {
                Game.window = Window.Leaderboard;
                leaderboardCanvas.toFront();
            } break;
        }
    }
}
