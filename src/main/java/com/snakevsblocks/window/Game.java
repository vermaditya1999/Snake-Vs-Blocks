package com.snakevsblocks.window;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.*;
import com.snakevsblocks.entity.burst.Burst;
import com.snakevsblocks.entity.burst.LargeBurst;
import com.snakevsblocks.entity.burst.SmallBurst;
import com.snakevsblocks.entity.token.*;
import com.snakevsblocks.gui.button.BackButton;
import com.snakevsblocks.gui.button.RestartButton;
import com.snakevsblocks.gui.button.ResumeButton;
import com.snakevsblocks.gui.button.WhiteBackButton;
import com.snakevsblocks.gui.label.CoinLabel;
import com.snakevsblocks.gui.label.Label;
import com.snakevsblocks.gui.label.ScoreLabel;
import com.snakevsblocks.gui.timer.MagnetTimer;
import com.snakevsblocks.gui.timer.ShieldTimer;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import com.snakevsblocks.util.Vector;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class Game extends Window {

    public static final String PATH = App.PATH + "game.ser";

    public static final Color BG_COLOR = Color.rgb(23, 29, 43);

    private int score;
    private int coins;
    private int curSpeed;
    private int gameSpeed;

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

    private ShieldTimer shieldTimer;
    private MagnetTimer magnetTimer;

    private BackButton backButton;
    private ResumeButton resumeButton;
    private RestartButton restartButton;

    public Game(WindowController wc, Canvas canvas) {
        super(wc, canvas);

        scoreLabel = new ScoreLabel();
        coinLabel = new CoinLabel();

        shieldTimer = new ShieldTimer();
        magnetTimer = new MagnetTimer();

        resumeButton = new ResumeButton(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
        backButton = new WhiteBackButton(App.TILE_SIZE / 2, App.TILE_SIZE / 2);
        restartButton = new RestartButton(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + App.TILE_SIZE * 2);

        loadNewGame();
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode() == KeyCode.ESCAPE) {
                    boolean resumed = !(paused || gameOver);
                    if (resumed) {
                        paused = true;
                    } else if (paused) {
                        paused = false;
                    } else if (gameOver) {
                        loadNewGame();
                        windowController.setWindow(Windows.MENU, mouseX, mouseY);
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Windows currentWindow = windowController.getCurrentWindow();
            if (currentWindow != Windows.GAME) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (gameOver) {
                    if (restartButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                        windowController.setWindow(Windows.MENU, mouseX, mouseY);
                    }
                } else if (paused) {
                    if (resumeButton.isHovered(mouseX, mouseY)) {
                        paused = false;
                    } else if (backButton.isHovered(mouseX, mouseY)) {
                        windowController.saveGame();
                        windowController.setWindow(Windows.MENU, mouseX, mouseY);
                    } else if (restartButton.isHovered(mouseX, mouseY)) {
                        loadNewGame();
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
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
                canvas.setCursor(Cursor.HAND);
            } else if (backButton.isHovered(mouseX, mouseY)) {
                canvas.setCursor(Cursor.HAND);
            } else {
                canvas.setCursor(Cursor.DEFAULT);
            }
        } else if (paused) {
            if (resumeButton.isHovered(mouseX, mouseY) ||
                    backButton.isHovered(mouseX, mouseY) ||
                    restartButton.isHovered(mouseX, mouseY)) {
                canvas.setCursor(Cursor.HAND);
            } else {
                canvas.setCursor(Cursor.DEFAULT);
            }
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        /*
         * The previous score of the game is set only when a game gets over.
         * Check if gameOver is already true so that setPrevScore should be called only once per game.
         */
        if (!gameOver && snake.isDead()) {
            windowController.addScore(score);
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
        gameSpeed = 4;
        curSpeed = gameSpeed;

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

        // 40% chances of a full row of blocks
        int choice = Random.nextInt(5);

        // Adding complete row of blocks
        if (choice <= 1) {
            Chain chain = new Chain(snake.getLength());
            for (int i = 1; i <= 5; i++) {

                if (chain.getBlockRow().get(i - 1) != null) {
                    blocks.add(chain.getBlockRow().get(i - 1));
                    //blocks.add(new Block(i, -2));


                    // 20% chance of a wall, given there is a block
                    int choose = Random.nextInt(5);
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
                    /* Probabilities of tokens:
                     * Shield : 0.2%
                     * Magnet : 0.2%
                     * Destroyer : 0.2%
                     * Coin : 2%
                     * PickupBall : 4%
                     */

                    int choose = Random.nextInt(1000);

                    if (choose <= 1) {
                        tokens.add(new Shield(i, -2));
                        break;

                    } else if (choose <= 3) {
                        tokens.add(new Magnet(i, -2));
                        break;

                    } else if (choose <= 5) {
                        tokens.add(new Destroyer(i, -2));
                        break;

                    } else if (choose <= 25) {
                        tokens.add(new Coin(i, -2));
                        break;

                    } else if (choose <= 85) {
                        tokens.add(new PickupBall(i, -2));
                        break;
                    }
                }
            }

            // 60% chances of partial row of blocks
        } else {
            for (int i = 1; i <= 5; i++) {

                // 33% chances of a block
                int choose = Random.nextInt(3);
                if (choose == 0) {
                    blocks.add(new Block(i, -2, snake.getLength()));

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
                    /* Probabilities of tokens:
                     * Shield : 0.2%
                     * Magnet : 0.2%
                     * Destroyer : 0.2%
                     * Coin : 2%
                     * PickupBall : 4%
                     */

                    choose = Random.nextInt(1000);

                    if (choose <= 1) {
                        tokens.add(new Shield(i, -2));
                        break;

                    } else if (choose <= 3) {
                        tokens.add(new Magnet(i, -2));
                        break;

                    } else if (choose <= 5) {
                        tokens.add(new Destroyer(i, -2));
                        break;

                    } else if (choose <= 25) {
                        tokens.add(new Coin(i, -2));
                        break;

                    } else if (choose <= 85) {
                        tokens.add(new PickupBall(i, -2));
                        break;
                    }
                }

            }
        }
    }


    private void updateGame() {
        trigger += curSpeed;
        if (trigger % (App.TILE_SIZE * 3) == 0) {
            populate();
            trigger = 0;
        } else if (trigger % App.TILE_SIZE == 0) {

            /* Probabilities of tokens:
             * Shield : 0.2%
             * Magnet : 0.2%
             * Destroyer : 0.2%
             * Coin : 2%
             * PickupBall : 6%
             */
            for (int i = 1; i <= 5; i++) {
                int choose = Random.nextInt(1000);

                if (choose <= 1) {
                    tokens.add(new Shield(i, -2));
                    break;

                } else if (choose <= 3) {
                    tokens.add(new Magnet(i, -2));
                    break;

                } else if (choose <= 5) {
                    tokens.add(new Destroyer(i, -2));
                    break;

                } else if (choose <= 25) {
                    tokens.add(new Coin(i, -2));
                    break;

                } else if (choose <= 85) {
                    tokens.add(new PickupBall(i, -2));
                    break;
                }
            }
        }

        /*
         * Blocks, walls and tokens are updated with the game curSpeed.
         * They are removed from the Collection if they are out of the screen.
         */

        // Update blocks
        Iterator blockIterator = blocks.iterator();
        while (blockIterator.hasNext()) {
            Block block = (Block) blockIterator.next();
            if (block.isOver()) {
                blockIterator.remove();
            } else {
                block.update(curSpeed);
            }
        }

        // Update walls
        Iterator wallIterator = walls.iterator();
        while (wallIterator.hasNext()) {
            Wall wall = (Wall) wallIterator.next();
            if (wall.isOver()) {
                wallIterator.remove();
            } else {
                wall.update(curSpeed);
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
                    magnetTimer.reset();
                } else if (token instanceof Shield) {
                    shieldTimer.reset();
                }

                // Add SmallBurst
                Vector pos = token.getPos();
                bursts.add(new SmallBurst(pos.x, pos.y));

                // Remove token as it is consumed
                tokenIterator.remove();
            }

            if (token.isOver()) {
                tokenIterator.remove();
            } else {
                token.update(curSpeed);
            }
        }

        // Update snake
        snake.update(mouseX, mouseY, walls, blocks);

        // Collision of snake with Blocks when shield is active
        if (shieldTimer.isActive()) {
            blockIterator = blocks.iterator();
            while (blockIterator.hasNext()) {
                Block block = (Block) blockIterator.next();
                Vector pos = block.getPos();
                Vector head = snake.getHeadVector();
                if ((Math.abs(head.x - pos.x) <= App.TILE_SIZE / 2 + SnakeBall.RADIUS) &&
                        head.y - (pos.y + App.TILE_SIZE / 2) <= SnakeBall.RADIUS / 2 && head.y - (pos.y + App.TILE_SIZE / 2) >= 0) {
                    bursts.add(new LargeBurst(head.x, head.y - SnakeBall.RADIUS));
                    score += block.getValue();
                    blockIterator.remove();
                }
            }
            shieldTimer.update();
        }

        // Attract coins when magnet is active
        if (magnetTimer.isActive()) {
            tokenIterator = tokens.iterator();
            while (tokenIterator.hasNext()) {
                Token token = (Token) tokenIterator.next();
                if (token instanceof Coin) {
                    Coin coin = (Coin) token;
                    Vector snakePos = snake.getHeadVector();
                    Vector coinPos = coin.getPos();
                    if (Math.abs(coinPos.y - snakePos.y) <= App.TILE_SIZE && Math.abs(coinPos.x - snakePos.x) <= App.SCREEN_WIDTH / 2) {
                        coin.attract(snake.getHeadVector());
                    }
                }
            }
            magnetTimer.update();
        }

        // Horizontal Collision of snake with blocks
        if (!snake.isDead()) {
            blockIterator = blocks.iterator();
            while (blockIterator.hasNext()) {
                Block block = (Block) blockIterator.next();
                Vector pos = block.getPos();
                Vector head = snake.getHeadVector();
                if ((Math.abs(head.x - pos.x) <= App.TILE_SIZE / 2 + SnakeBall.RADIUS) &&
                        head.y - (pos.y + App.TILE_SIZE / 2) <= SnakeBall.RADIUS / 2 && head.y - (pos.y + App.TILE_SIZE / 2) >= 0) {

                    // Add burst
                    Vector snakePos = snake.getHeadVector();
                    if (block.expand()) {
                        blockIterator.remove();
                        bursts.add(new LargeBurst(snakePos.x, snakePos.y - SnakeBall.RADIUS));
                        snake.removeTail();
                    } else {
                        bursts.add(new SmallBurst(snakePos.x, snakePos.y));
                        snake.removeHead();  // Burst snake Head
                    }

                    score++;  // Increase score
                    break;
                }
            }
        }

        if (!snake.isDead() && !snake.inPos()) {
            curSpeed = 0;
        } else {
            curSpeed = gameSpeed;
        }

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

        // Show Timers
        if (shieldTimer.isActive()) {
            shieldTimer.run(gc);
        }

        if (magnetTimer.isActive()) {
            magnetTimer.run(gc);
        }

        // Run bursts
        runBursts();
    }

    private void showPauseOverlay() {

        gc.setFill(new Color(0, 0, 0, 0.75));
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

//        gc.applyEffect(new BoxBlur(10, 10, 10));

        backButton.show(gc);
        resumeButton.show(gc);
        restartButton.show(gc);
    }

    private void showGameOver() {

        gc.setFill(new Color(0, 0, 0, 0.95));
        gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);

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

    public void serialize() {
        ObjectOutputStream out = null;
        try {
            try {
                out = new ObjectOutputStream(new FileOutputStream(Game.PATH));
                out.writeObject(this);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}