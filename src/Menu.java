import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Menu extends Window {

    private Button pgBtn;
    private Button lbBtn;

    public Menu(WindowController wc, Group root) {
        super(wc, root);

        lbBtn = new Button(2 * Game.TILE_SIZE - Game.TILE_SIZE / 2, 6 * Game.TILE_SIZE);
        pgBtn = new Button(3 * Game.TILE_SIZE + Game.TILE_SIZE / 2, 6 * Game.TILE_SIZE);
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            }

            double mouseX = event.getX();
            double mouseY = event.getY();

            // LeaderBoard button
            if (lbBtn.isHovered(mouseX, mouseY)) {
                windowController.setWindow(Windows.LeaderBoard);
            }

            // Play Game button
            if (pgBtn.isHovered(mouseX, mouseY)) {
                windowController.setWindow(Windows.GamePlay);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            }

            double mouseX = event.getX();
            double mouseY = event.getY();

            // LeaderBoard button
            if (lbBtn.isHovered(mouseX, mouseY) || pgBtn.isHovered(mouseX, mouseY)) {
                canvas.setCursor(Cursor.HAND);
            } else {
                canvas.setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Game name
        gc.setFill(Color.WHITE);
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 2 * Game.TILE_SIZE);
        gc.fillText("Vs", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE - 10);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - 20);

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
