import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GamePlay extends Window {

    // Temporary burst for demonstration
    ArrayList<Burst> bursts = new ArrayList<Burst>();

    // Temporary Blocks for testing
    ArrayList<Block> blocks = new ArrayList<Block>();

    // The initial speed should be 2 and it should increase in multiples of 2 only
    private double speed;
    private boolean paused;
    private BackButton backButton;
    private ResumeButton resumeButton;
    private RestartButton restartButton;

    {
        resumeButton = new ResumeButton();
        backButton = new BackButton();
        restartButton = new RestartButton();
    }

    public GamePlay(WindowController wc, Group root) {
        super(wc, root);

        newGamePlay();
    }

    private void newGamePlay() {
        speed = 4;
        paused = false;
        blocks.clear();

        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            int choose = random.nextInt(2);
            if (choose == 1) {
                blocks.add(new Block(i, 1));
            }
        }
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
                if (paused) {
                    if (resumeButton.isHovered(mouseX, mouseY)) {
                        paused = false;
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        newGamePlay();
                        resetMouseVars();
                        windowController.setWindow(Windows.Menu);
                    } else if (restartButton.isHovered(mouseX, mouseY)) {
                        newGamePlay();
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.GamePlay) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    @Override
    public void show() {

        if (paused) {
            if (resumeButton.isHovered(mouseX, mouseY) || backButton.isHovered(mouseX, mouseY) || restartButton.isHovered(mouseX, mouseY)) {
                canvas.setCursor(Cursor.HAND);
            } else {
                canvas.setCursor(Cursor.DEFAULT);
            }
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

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

            gc.applyEffect(new BoxBlur(10, 10, 10));

            backButton.show(gc);
            resumeButton.show(gc);
            restartButton.show(gc);
        }
    }
}
