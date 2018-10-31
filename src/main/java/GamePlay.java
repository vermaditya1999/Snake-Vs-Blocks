import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GamePlay extends Window {

    // Temporary field for demonstration
    ArrayList<Burst> bursts = new ArrayList<Burst>();

    // Temporary Blocks for testing
    ArrayList<Block> blocks = new ArrayList<Block>();

    // The initial speed should be 2 and it should increase in multiples of 2 only
    private double speed;

    // True if game is paused
    private boolean paused;

    private PlayButton playButton;

    private BackButton backButton;

    public GamePlay(WindowController wc, Group root) {

        super(wc, root);

        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            int choose = random.nextInt(2);
            if (choose == 1) {
                blocks.add(new Block(i, 1));
            }
        }

        speed = 4;
        paused = false;
        playButton = new PlayButton(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2);
        backButton = new BackButton();
    }

    @Override
    protected void loadDefaults() {

        super.loadDefaults();

        canvas.setCursor(Cursor.NONE);
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.GamePlay) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode() == KeyCode.ESCAPE) {
                    paused = !paused;
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.GamePlay) {
                windowController.passEvent(currentWindow, event);
            } else {
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (paused) {
                    if (playButton.isHovered(mouseX, mouseY)) {
                        paused = !paused;
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        windowController.setWindow(Windows.Menu);
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.GamePlay) {
                windowController.passEvent(currentWindow, event);
            } else {
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (paused) {
                    if (playButton.isHovered(mouseX, mouseY) || backButton.isHovered(mouseX, mouseY)) {
                        canvas.setCursor(Cursor.HAND);
                    } else {
                        canvas.setCursor(Cursor.DEFAULT);
                    }
                }
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

            // Update blocks
            for (Block block : blocks) {
                block.update(speed);
            }
        }

        // Show the game here
        for (Block block : blocks) {
            block.show(gc);
        }

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

            gc.setFill(new Color(0, 0, 0, 0.75));
            gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

            // Blur the background
            gc.applyEffect(new GaussianBlur());

            // Resume game button
            playButton.show(gc);

            backButton.show(gc);
        }
    }

    @Override
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }
}
