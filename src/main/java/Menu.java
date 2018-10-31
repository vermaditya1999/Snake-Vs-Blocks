import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Window {

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
                    windowController.setWindow(Windows.LeaderBoard);
                }

                if (pgBtn.isHovered(mouseX, mouseY)) {
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

        // Set mouse pointer
        if (lbBtn.isHovered(mouseX, mouseY) || pgBtn.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Color.rgb(79,53,88));
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Game name
        gc.setFont(new Font("Consolas", 60));
        gc.setFill(Color.WHITE);
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.75);
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
