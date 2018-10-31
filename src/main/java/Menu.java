import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Window {

    public static final Color BG_COLOR = Color.rgb(79,53,88);

    private MenuButton pgBtn;
    private MenuButton lbBtn;

    private double mouseX;
    private double mouseY;

    public Menu(WindowController wc, Group root) {
        super(wc, root);

        pgBtn = new MenuButton("Start Game", Game.SCREEN_HEIGHT / 2);
        lbBtn = new MenuButton("Leaderboard", Game.SCREEN_HEIGHT / 2 + Game.TILE_SIZE);
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

    private void resetMouseVars() {
        mouseX = 0;
        mouseY = 0;
    }

    @Override
    public void show() {

        // Set hover properties of buttons
        pgBtn.setHovered(pgBtn.isHovered(mouseX, mouseY));
        lbBtn.setHovered(lbBtn.isHovered(mouseX, mouseY));

        // Set mouse pointer
        if (pgBtn.isHovered(mouseX, mouseY) || lbBtn.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Menu.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Display Game title
        gc.setFill(Color.WHITE);

        gc.setFont(new Font("Consolas", 75));
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.75);

        gc.setFont(new Font("Consolas", 65));
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 2.5);

        // LeaderBoard button
        lbBtn.show(gc);

        // Play game button
        pgBtn.show(gc);
    }

    @Override
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }
}
