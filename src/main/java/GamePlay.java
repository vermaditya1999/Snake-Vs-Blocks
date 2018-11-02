import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePlay extends Window {

    // Temporary burst for demonstration
    ArrayList<Burst> bursts = new ArrayList<Burst>();

    // Temporary Blocks for testing
    ArrayList<Block> blocks = new ArrayList<Block>();

    // Temporary Walls for testing
    ArrayList<Wall> walls = new ArrayList<Wall>();

    // Temporary Tokens for testing
    ArrayList<Token> tokens = new ArrayList<Token>();

    // The initial speed should be 2 and it should increase in multiples of 2 only
    private double speed;
    private boolean paused;
    private BackButton backButton;
    private ResumeButton resumeButton;
    private RestartButton restartButton;

    private int trigger;

    private Random random;

    {
        resumeButton = new ResumeButton();
        backButton = new BackButton();
        restartButton = new RestartButton();
        random = new Random();
    }

    public GamePlay(WindowController wc, Group root) {
        super(wc, root);

        newGamePlay();
    }

    private void newGamePlay() {
        speed = 4;
        trigger = 0;
        paused = false;

        // Clear blocks
        blocks.clear();

        // Clear walls
        walls.clear();

        // Clear tokens
        tokens.clear();

        // Populate the game
        populate();
    }

    private void populate() {

        for (int i = 1; i <= 5; i++) {

            // 30% chances of a block
            int choose = random.nextInt(3);
            if (choose == 1) {
                blocks.add(new Block(i, -2));

                // 50% chance of a wall, given there is a block
                choose = random.nextInt(2);
                if (choose == 1) {
                    walls.add(new Wall(i, -2));
                }
            } else {

                // No Block, Wall has been added, a token can be added
                choose = random.nextInt(15);
                if (choose == 1) {
                    choose = random.nextInt(4);
                    switch (choose) {
                        case 0:
                            tokens.add(new Shield(i, -2));
                            break;
                        case 1:
                            tokens.add(new Magnet(i, -2));
                            break;
                        case 2:
                            tokens.add(new PickupBall(i, -2));
                            break;
                        case 3:
                            tokens.add(new Destroyer(i, -2));
                            break;
                    }
                }
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

                        // When resume button is pressed, set paused = true
                        paused = false;
                    } else if (backButton.isHovered(mouseX, mouseY)) {

                        // When back button is pressed, reset and create new game, then go back to main menu
                        newGamePlay();
                        resetMouseVars();
                        windowController.setWindow(Windows.Menu);
                    } else if (restartButton.isHovered(mouseX, mouseY)) {

                        // When restart button is pressed, simply create a new gameplay
                        newGamePlay();
                    }
                } else {
                    bursts.add(new Burst(mouseX, mouseY));
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.GamePlay) {
                windowController.passEvent(currentWindow, event);
            } else {

                // Whenever mouse is moved, update the mouseX and mouseY variables
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

            trigger += speed;
            if (trigger % (Game.TILE_SIZE * 5) == 0) {
                populate();
                trigger = 0;
            } else if (trigger % Game.TILE_SIZE == 0) {

                // We can add a token here, probability 1% each
                for (int i = 1; i <= 5; i++) {
                    int choose = random.nextInt(100);
                    switch (choose) {
                        case 10:
                            tokens.add(new Shield(i, -2));
                            break;
                        case 30:
                            tokens.add(new Magnet(i, -2));
                            break;
                        case 40:
                            tokens.add(new PickupBall(i, -2));
                            break;
                        case 70:
                            tokens.add(new Destroyer(i, -2));
                            break;
                    }
                }
            }

            // Update Blocks
            Iterator blockIterator = blocks.iterator();
            while (blockIterator.hasNext()) {
                Block block = (Block) blockIterator.next();
                if (block.isOver()) {
                    blockIterator.remove();
                } else {
                    block.update(speed);
                }
            }

            // Update Walls
            Iterator wallIterator = walls.iterator();
            while (wallIterator.hasNext()) {
                Wall wall = (Wall) wallIterator.next();
                if (wall.isOver()) {
                    wallIterator.remove();
                } else {
                    wall.update(speed);
                }
            }

            // Update tokens
            Iterator tokenIterator = tokens.iterator();
            while (tokenIterator.hasNext()) {
                Token token = (Token) tokenIterator.next();
                if (token.isOver()) {
                    tokenIterator.remove();
                } else {
                    token.update(speed);
                }
            }
        }

        // Show the game here, cause even if the game is pause, we have to show it

        // Show blocks
        for (Block block : blocks) {
            block.show(gc);
        }

        // Show walls
        for (Wall wall : walls) {
            wall.show(gc);
        }

        // Show tokens
        for (Token token : tokens) {
            token.show(gc);
        }

        // Update and show bursts, they aren't paused. Will look kinda cool :P
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
