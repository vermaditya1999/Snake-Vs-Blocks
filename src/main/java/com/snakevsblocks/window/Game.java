package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.Block;
import com.snakevsblocks.entity.Snake;
import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.entity.Wall;
import com.snakevsblocks.entity.burst.Burst;
import com.snakevsblocks.entity.burst.LargeBurst;
import com.snakevsblocks.entity.burst.SmallBurst;
import com.snakevsblocks.entity.token.*;
import com.snakevsblocks.gui.button.BackButton;
import com.snakevsblocks.gui.button.RestartButton;
import com.snakevsblocks.gui.button.ResumeButton;
import com.snakevsblocks.gui.label.CoinLabel;
import com.snakevsblocks.gui.label.Label;
import com.snakevsblocks.gui.label.ScoreLabel;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
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

    public static final Color BG_COLOR = Color.rgb(23, 29, 43);

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

        resumeButton = new ResumeButton(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
        backButton = new BackButton(App.TILE_SIZE / 2, App.TILE_SIZE / 2);
        restartButton = new RestartButton(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE * 2);

        loadNewGame();
    }

    @Override
    protected void addEventHandlers() {

        addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode() == KeyCode.ESCAPE) {
                    if (!(paused || gameOver)) {
                        paused = true;
                    } else if (paused) {
                        paused = false;
                    } else {
                        exitGame();
                    }
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
                        exitGame();
                    }
                } else if (paused) {
                    if (resumeButton.isHovered(mouseX, mouseY)) {
                        paused = false;
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        exitGame();
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
            } else {
                setCursor(Cursor.DEFAULT);
            }
        } else {
            setCursor(Cursor.DEFAULT);
        }

        // Check if gameOver is already true, so that setPrevScore should be called only once per game
        if (!gameOver && snake.isDead()) {
            windowController.setPrevScore(score);
            gameOver = true;
        }

        // Update game if it is running
        if (!(paused || gameOver)) {
            updateGame();
        }

        showGamePlay();

        if (paused) {
            showPauseOverlay();
        } else if (gameOver) {
            showGameOver();
        }
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

            // 33% chances of a block
            int choose = Random.nextInt(3);
            if (choose == 0) {
                blocks.add(new Block(i, -2));

                // 20% chance of a wall, given there is a block
                choose = Random.nextInt(5);
                if (choose == 0) {

                    // Check any overlap of existing tokens with wall
                    boolean flag = false;
                    for (Token token : tokens) {
                        Vector pos = token.getPos();
                        if (pos.x == ((i - 1) * App.TILE_SIZE + App.TILE_SIZE / 2) &&
                                (pos.y == -App.TILE_SIZE / 2 || pos.y == -2 * App.TILE_SIZE + App.TILE_SIZE / 2)) {
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

    private void updateGame() {
        trigger += speed;
        if (trigger % (App.TILE_SIZE * 3) == 0) {
            populate();
            trigger = 0;
        } else if (trigger % App.TILE_SIZE == 0) {

            /* Probabilities of tokens:
             * Shield : 0.5%
             * Magnet : 0.5%
             * Destroyer : 0.5%
             * Coin : 2%
             * PickupBall : 4%
             */
            for (int i = 1; i <= 5; i++) {
                int choose = Random.nextInt(200);

                if (choose == 0) {
                    tokens.add(new Shield(i, -2));
                    break;

                } else if (choose == 1) {
                    tokens.add(new Magnet(i, -2));
                    break;

                } else if (choose == 2) {
                    tokens.add(new Destroyer(i, -2));
                    break;

                } else if (choose <= 6) {
                    tokens.add(new Coin(i, -2));
                    break;

                } else if (choose <= 14) {
                    tokens.add(new PickupBall(i, -2));
                    break;
                }
            }
        }

        /*
         * Blocks, walls and tokens are updated with the game speed.
         * They are removed from the Collection if they are out of the screen.
         */

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

            // Try to consume the tokens
            token.consume(snake.getHeadVector());

            // Handle if consumed
            if (token.isConsumed()) {
                if (token instanceof Coin) {
                    coins++;
                } else if (token instanceof PickupBall) {
                    int value = ((PickupBall) token).getValue();
                    score += value;
                    snake.addBalls(value);
                } else if (token instanceof Destroyer) {

                    // Destroy all blocks on the screen
                    Iterator it = blocks.iterator();
                    while (it.hasNext()) {
                        Block block = (Block) it.next();
                        if (block.isOnScreen()) {
                            Vector pos = block.getPos();
                            bursts.add(new LargeBurst(pos.x, pos.y));
                            score += block.getValue();
                            it.remove();
                        }
                    }
                } else if (token instanceof Magnet) {

                } else if (token instanceof Shield) {

                }

                // Add SmallBurst
                bursts.add(new SmallBurst(token.getX(), token.getY()));

                // Remove token as it is consumed
                tokenIterator.remove();
            }

            if (token.isOver()) {
                tokenIterator.remove();
            } else {
                token.update(speed);
            }
        }

        // Temporary collision of snake with blocks
        blockIterator = blocks.iterator();
        while (blockIterator.hasNext()) {
            Vector pos = ((Block) blockIterator.next()).getPos();
            Vector head = snake.getHeadVector();
            if ((Math.abs(head.x - pos.x) <= App.TILE_SIZE / 2) &&
                    Math.abs(head.y - (pos.y + App.TILE_SIZE / 2)) <= SnakeBall.RADIUS / 2) {
                blockIterator.remove();
                bursts.add(new LargeBurst(head.x, head.y - SnakeBall.RADIUS));
            }
        }

        // Update snake
        snake.update(mouseX, mouseY);

        // Update score and coin labels
        scoreLabel.update(Integer.toString(score));
        coinLabel.update(Integer.toString(coins));
    }

    private void showGamePlay() {

        // Set background
        gc.setFill(Game.BG_COLOR);
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

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

        // Run bursts
        runBursts();
    }

    private void showPauseOverlay() {

        gc.setFill(new Color(0, 0, 0, 0.75));
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        gc.applyEffect(new BoxBlur(10, 10, 10));

        backButton.show(gc);
        resumeButton.show(gc);
        restartButton.show(gc);
    }

    private void showGameOver() {

        gc.setFill(new Color(0, 0, 0, 0.75));
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

        gc.applyEffect(new BoxBlur(10, 10, 10));
        gc.setFont(Font.CONSOLAS_LARGE);
        gc.setFill(Color.WHITE);
        gc.fillText("GAME OVER", App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 - App.TILE_SIZE);

        gc.setFont(Font.CONSOLAS_LARGE);
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(score), App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE / 2);

        backButton.show(gc);
        restartButton.show(gc);
    }

    private void runBursts() {
        gc.setFill(Color.WHITE);
        Iterator burstIterator = bursts.iterator();
        while (burstIterator.hasNext()) {
            Burst burst = (Burst) burstIterator.next();
            if (burst.isOver()) {
                burstIterator.remove();
            } else {
                burst.run(gc);
            }
        }
    }

    private void exitGame() {
        loadNewGame();
        windowController.setWindow(Windows.MENU, mouseX, mouseY);
    }
}