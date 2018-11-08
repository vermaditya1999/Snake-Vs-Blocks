package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.Block;
import com.snakevsblocks.entity.Snake;
import com.snakevsblocks.entity.Wall;
import com.snakevsblocks.entity.burst.Burst;
import com.snakevsblocks.entity.burst.LargeBurst;
import com.snakevsblocks.entity.burst.SmallBurst;
import com.snakevsblocks.entity.token.*;
import com.snakevsblocks.gui.*;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.LinkedList;

public class Game extends Window {

    public static final Color BG_COLOR = Color.BLACK;

    private int score;
    private int coins;
    private int speed;
    private int trigger;
    private boolean paused;
    private boolean gameOver;

    private Snake snake;

    private LinkedList<Burst> bursts;
    private LinkedList<Block> blocks;
    private LinkedList<Wall> walls;
    private LinkedList<Token> tokens;

    private Label scoreLabel;
    private Label coinLabel;

    private BackButton backButton;
    private ResumeButton resumeButton;
    private RestartButton restartButton;

    public Game(WindowController wc, Group root) {
        super(wc, root);

        scoreLabel = new ScoreLabel();
        coinLabel = new CoinLabel();

        resumeButton = new ResumeButton();
        backButton = new BackButton();
        restartButton = new RestartButton();

        loadNewGame();
    }

    public void loadNewGame() {
        coins = 0;
        score = 0;
        trigger = 0;
        speed = 4;
        paused = false;
        gameOver = false;

        mouseX = App.SCREEN_WIDTH / 2;

        snake = new Snake();

        bursts = new LinkedList<Burst>();
        blocks = new LinkedList<Block>();
        walls = new LinkedList<Wall>();
        tokens = new LinkedList<Token>();

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

                // No Block/ Wall has been added, a token can be added
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
                if (gameOver) {
                    if (restartButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                        windowController.setWindow(Windows.MENU);
                    }
                } else if (paused) {
                    if (resumeButton.isHovered(mouseX, mouseY)) {
                        paused = false;
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                        windowController.setWindow(Windows.MENU);
                    } else if (restartButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                    }
                } else {
                    // Temporary: Remove a snakeBall on click
                    snake.removeBall();
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    @Override
    public void show() {

        // Set Cursor
        if (gameOver) {
            if (restartButton.isHovered(mouseX, mouseY)) {
                setCursor(Cursor.HAND);
            } else if (backButton.isHovered(mouseX, mouseY)) {
                setCursor(Cursor.HAND);
            } else {
                setCursor(Cursor.DEFAULT);
            }
        } else if (paused) {
            if (resumeButton.isHovered(mouseX, mouseY) ||
                    backButton.isHovered(mouseX, mouseY) ||
                    restartButton.isHovered(mouseX, mouseY)) {
                setCursor(Cursor.HAND);
            }
        } else {
            setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Game.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        // Update and show the game
        if (snake.isDead()) {
            gameOver = true;
            showGameOver();
        } else {
            if (!paused) {
                updateGame();
            }
            showGame();
            runBursts();
            if (paused) {
                showPauseOverlay();
            }
        }
    }

    private void updateGame() {
        trigger += speed;
        if (trigger % (App.TILE_SIZE * 3) == 0) {
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
            Class tokenClass = token.getClass();

            // Collide the tokens
            token.collide(snake.getHeadVector());

            // Handle collision
            if (token.isConsumed()) {
                if (tokenClass.equals(Coin.class)) {
                    coins++;
                } else if (tokenClass.equals(PickupBall.class)) {
                    int value = ((PickupBall) token).getValue();
                    score += value;
                    snake.addBalls(value);
                } else if (tokenClass.equals(Destroyer.class)) {
                    Iterator it = blocks.iterator();
                    while (it.hasNext()) {
                        Block block = (Block) it.next();
                        if (block.isOnScreen()) {
                            bursts.add(new LargeBurst(block.getX(), block.getY()));
                            score += block.getValue();
                            it.remove();
                        }
                    }
                }

                // Add SmallBurst
                bursts.add(new SmallBurst(token.getX(), token.getY()));

                // Remove token
                tokenIterator.remove();
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

        // Update score and coin labels
        scoreLabel.update(Integer.toString(score));
        coinLabel.update(Integer.toString(coins));
    }

    private void showGame() {

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

        // Show Labels
        scoreLabel.show(gc);
        coinLabel.show(gc);
    }

    private void showPauseOverlay() {
        gc.setFill(new Color(0, 0, 0, 0.75));
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        gc.applyEffect(new BoxBlur(10, 10, 10));

        backButton.show(gc, Color.WHITE);
        resumeButton.show(gc);
        restartButton.show(gc);
    }

    private void showGameOver() {
        gc.setFont(Font.CONSOLAS_LARGE);
        gc.setFill(Color.WHITE);
        gc.fillText("GAME OVER", App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE);

        gc.setFont(Font.CONSOLAS_LARGE);
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(score), App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE / 2);

        backButton.show(gc, Color.WHITE);
        restartButton.show(gc);
    }

    private void runBursts() {
        gc.setFill(Color.WHITE);
        for (int i = bursts.size() - 1; i >= 0; i--) {
            Burst burst = bursts.get(i);

            if (burst.isOver()) {
                bursts.remove(i);
            } else {
                burst.run(gc);
            }
        }
    }
}