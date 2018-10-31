import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Window {

    public static final Color BG_COLOR = Color.rgb(79,53,78);

    private MenuButton pgBtn;
    private MenuButton lbBtn;

    {
        pgBtn = new MenuButton("Start Game", Game.SCREEN_HEIGHT / 2);
        lbBtn = new MenuButton("Leaderboard", Game.SCREEN_HEIGHT / 2 + Game.TILE_SIZE);
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

                if (lbBtn.isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.LeaderBoard);
                }

                if (pgBtn.isHovered(mouseX, mouseY)) {
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

        pgBtn.setHovered(pgBtn.isHovered(mouseX, mouseY));
        lbBtn.setHovered(lbBtn.isHovered(mouseX, mouseY));

        if (pgBtn.isHovered(mouseX, mouseY) || lbBtn.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        gc.setFill(Menu.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        gc.setFill(Color.WHITE);

        gc.setFont(new Font("Consolas", 75));
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.75);

        gc.setFont(new Font("Consolas", 65));
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 2.5);

        lbBtn.show(gc);

        pgBtn.show(gc);
    }
}
