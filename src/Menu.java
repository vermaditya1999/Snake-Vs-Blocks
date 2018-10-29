import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Menu extends Window {

    private Button playGameBtn;
    private Button leaderBoardBtn;

    public Menu(WindowController wc, Group root) {
        super(wc, root);

        playGameBtn = new Button(Game.TILE_SIZE, (Game.NUM_ROWS - 3) * Game.TILE_SIZE);
        leaderBoardBtn = new Button(3 * Game.TILE_SIZE, (Game.NUM_ROWS - 3) * Game.TILE_SIZE);
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
            if (leaderBoardBtn.isHovered(mouseX, mouseY)) {
                windowController.setWindow(Windows.LeaderBoard);
            }

            // Play Game button
            if (playGameBtn.isHovered(mouseX, mouseY)) {
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
            if (leaderBoardBtn.isHovered(mouseX, mouseY) || playGameBtn.isHovered(mouseX, mouseY)) {
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
        leaderBoardBtn.show(gc);

        // Play game button
        playGameBtn.show(gc);
    }

    @Override
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }
}
