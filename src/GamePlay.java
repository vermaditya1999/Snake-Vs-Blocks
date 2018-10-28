import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GamePlay extends Window {

    // Temporary field for demonstration
    ArrayList<Burst> bursts = new ArrayList<Burst>();

    // True if game is paused
    private boolean paused;

    PlayButton playButton;

    public GamePlay(WindowController wc, Group root) {
        super(wc, root);

        paused = false;
//        playButton = new PlayButton(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2);
    }

    @Override
    protected void loadDefaults() {
        super.loadDefaults();

        canvas.setCursor(Cursor.NONE);
    }

    @Override
    protected void addEventHandlers() {
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                windowController.setWindow(Windows.Menu);
                paused = !paused;
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Burst for demonstration
            bursts.add(new Burst(mouseX, mouseY));
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            if (paused) {
//                if (playButton.isHovered(mouseX, mouseY)) {
//                    canvas.setCursor(Cursor.HAND);
//                } else {
                    canvas.setCursor(Cursor.DEFAULT);
//                }
            } else {
                canvas.setCursor(Cursor.NONE);
            }

        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Temporary text
//        gc.setFill(Color.WHITE);
//        gc.fillText("GamePlay", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);

        /*
         * The pause overlay is shown when the escape key is pressed.
         * The paused variable is set true when the pause overlay is shown.
         */
        if (!paused) {

            // Update the game here

        }

        // Show the game here

        // Bursts aren't paused
        gc.setFill(Color.WHITE);
        for (int i = bursts.size() - 1; i >= 0; i--) {
            Burst b = bursts.get(i);

            if (b.isOver()) {
                bursts.remove(i);
            } else {
                b.show(gc);
            }
        }

        // Show the pause overlay
        if (paused) {

            // Blur the background
            gc.applyEffect(new GaussianBlur());

            // Resume game button
//            playButton.show(gc);
        }
    }
}
