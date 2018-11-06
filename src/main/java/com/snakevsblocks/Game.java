package com.snakevsblocks;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Iterator;
import java.util.LinkedList;

public class Game extends Window {

    public static final Color BG_COLOR = Color.rgb(57, 8, 49);

    private LinkedList<Burst> bursts;

    private LinkedList<Block> blocks;

    private LinkedList<Wall> walls;

    private LinkedList<Token> tokens;

    private Snake snake;

    private int score;
    private int numCoins;
    private int trigger;
    private double speed;
    private boolean paused;
    private BackButton backButton;
    private ResumeButton resumeButton;
    private RestartButton restartButton;

    public Game(WindowController wc, Group root) {
        super(wc, root);

        // Initialize buttons
        resumeButton = new ResumeButton();
        backButton = new BackButton();
        restartButton = new RestartButton();

        // Initialize collections
        bursts = new LinkedList<Burst>();
        blocks = new LinkedList<Block>();
        walls = new LinkedList<Wall>();
        tokens = new LinkedList<Token>();

        // Initialize Snake
        snake = new Snake();

        // Load a new game
        loadNewGame();
    }

    public void loadNewGame() {
        numCoins = 0;
        score = 0;
        speed = 4;
        trigger = 0;
        paused = false;

        mouseX = App.SCREEN_WIDTH / 2;
        mouseY = App.SCREEN_HEIGHT / 2 + App.TILE_SIZE;

        // Set mouse cursor
        setCursor(Cursor.NONE);

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

            // 50% chances of a block
            int choose = Random.nextInt(4);
            if (choose <= 1) {
                blocks.add(new Block(i, -2));

                // 33% chance of a wall, given there is a block
                choose = Random.nextInt(3);
                if (choose == 1) {

                    // Check any overlap of existing tokens with wall
                    boolean flag = false;
                    for (Token token : tokens) {
                        if (token.pos.x == ((i - 1) * App.TILE_SIZE + App.TILE_SIZE / 2) &&
                                (token.pos.y == -App.TILE_SIZE / 2 || token.pos.y == -2 * App.TILE_SIZE + App.TILE_SIZE / 2)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        walls.add(new Wall(i, -2));
                    }
                }
            } else {

                // No Block, Wall has been added, a token can be added
                choose = Random.nextInt(15);
                if (choose == 1) {
                    choose = Random.nextInt(5);
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
                        case 4:
                            tokens.add(new Coin(i, -2));
                            break;
                    }
                }
            }
        }
    }

    @Override
    protected void addEventHandlers() {

        addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (!paused && event.getCode() == KeyCode.ESCAPE) {
                    paused = true;
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (paused) {
                    if (resumeButton.isHovered(mouseX, mouseY)) {

                        // When resume button is pressed, set paused = true
                        paused = false;
                        setCursor(Cursor.NONE);
                    } else if (backButton.isHovered(mouseX, mouseY)) {

                        // When back button is pressed, load new game, then go back to main menu
                        loadNewGame();
                        windowController.setWindow(Windows.MENU);
                    } else if (restartButton.isHovered(mouseX, mouseY)) {

                        // When restart button is pressed, simply create a new gameplay
                        loadNewGame();
                    }
                } else {
                    bursts.add(new SmallBurst(mouseX, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE));
                    snake.removeBall();
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
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
                setCursor(Cursor.HAND);
            } else {
                setCursor(Cursor.DEFAULT);
            }
        }

        // Set background
        gc.setFill(Game.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        /*
         * The pause overlay is shown when the escape key is pressed.
         * The paused variable is set true when the pause overlay is shown.
         */

        // Update the game here
        if (!paused) {
            trigger += speed;
            if (trigger % (App.TILE_SIZE * 5) == 0) {
                populate();
                trigger = 0;
            } else if (trigger % App.TILE_SIZE == 0) {

                /* Probabilities of tokens:
                 * PickupBall : 5%
                 * Coin : 3%
                 * Magnet : 1%
                 * Shield : 1%
                 * Destroyer : 1%
                 */
                for (int i = 1; i <= 5; i++) {
                    int choose = Random.nextInt(100);

                    if (choose == 1) {
                        tokens.add(new Shield(i, -2));
                        break;

                    } else if (choose == 2) {
                        tokens.add(new Magnet(i, -2));
                        break;

                    } else if (choose == 3) {
                        tokens.add(new Destroyer(i, -2));
                        break;

                    } else if (choose <= 8) {
                        tokens.add(new PickupBall(i, -2));
                        break;

                    } else if (choose <= 11) {
                        tokens.add(new Coin(i, -2));
                        break;
                    }
                }
            }

            // Update blocks
            Iterator blockIterator = blocks.iterator();
            while (blockIterator.hasNext()) {
                Block block = (Block) blockIterator.next();
                if (block.isOver()) {
                    blockIterator.remove();
                } else {
                    block.update(speed);
                }
            }

            // Update walls
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

                // Collide the tokens
                token.collide(snake.getHeadVector());
                if (token.isConsumed()) {
                    if (token.getClass().equals(Coin.class)) {
                        numCoins++;
                        tokenIterator.remove();
                    } else if (token.getClass().equals(PickupBall.class)) {
                        int value = ((PickupBall) token).getValue();
                        score += value;
                        snake.addBalls(value);
                        tokenIterator.remove();
                    } else if (token.getClass().equals(Destroyer.class)) {
                        tokenIterator.remove();
                        Iterator it = blocks.iterator();
                        while (it.hasNext()) {
                            Block block = (Block) it.next();
                            if (block.isOnScreen()) {
                                bursts.add(new LargeBurst(block.getPosX(), block.getPosY()));
                                it.remove();
                            }
                        }
                    }
                }

                // Remove tokens if they are dead
                if (token.isDead()) {
                    tokenIterator.remove();
                } else {
                    token.update(speed);
                }
            }

            // Update snake
            snake.update(mouseX, mouseY);

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

        // Show snake
        snake.show(gc);

        // Show score
        gc.setFont(new Font("Consolas", 30));
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(score), App.TILE_SIZE / 2, App.TILE_SIZE / 2);

        // Show total coins collected
        gc.setFill(Color.YELLOW);
        gc.fillOval(App.SCREEN_WIDTH - App.TILE_SIZE - Token.RADIUS,
                App.TILE_SIZE / 2 - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);

        gc.setFont(new Font("Consolas", 30));
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(numCoins), App.SCREEN_WIDTH - App.TILE_SIZE / 2,
                App.TILE_SIZE / 2);

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
            gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

            gc.applyEffect(new BoxBlur(10, 10, 10));

            backButton.show(gc, Color.WHITE);
            resumeButton.show(gc);
            restartButton.show(gc);
        }
    }
}