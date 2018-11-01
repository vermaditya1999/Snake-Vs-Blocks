import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Window {

    public static final Color BG_COLOR = Color.rgb(79,53,78);

    private MenuButton startGameButton;
    private MenuButton leaderBoardButton;

    {
        startGameButton = new MenuButton("Start Game", Game.SCREEN_HEIGHT / 2);
        leaderBoardButton = new MenuButton("Leaderboard", Game.SCREEN_HEIGHT / 2 + Game.TILE_SIZE);
    }

    public Menu(WindowController wc, Group root) {
        super(wc, root);
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();

            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            } else {

                if (leaderBoardButton.isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.LeaderBoard);
                }

                if (startGameButton.isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.GamePlay);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }

        });
    }

    @Override
    public void show() {

        startGameButton.setHovered(startGameButton.isHovered(mouseX, mouseY));
        leaderBoardButton.setHovered(leaderBoardButton.isHovered(mouseX, mouseY));

        if (startGameButton.isHovered(mouseX, mouseY) || leaderBoardButton.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        gc.setFill(Menu.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        gc.setFill(Color.WHITE);

        gc.setFont(new Font("Consolas", 65));
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.75);
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 2.5);

        leaderBoardButton.show(gc);

        startGameButton.show(gc);
    }
}