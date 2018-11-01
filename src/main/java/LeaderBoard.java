import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

public class LeaderBoard extends Window {

    public static final Color BG_COLOR = Color.rgb(79,53,78);

    private ArrayList<EntryBar> entryBars;
    private int maxSize;
    private BackButton backButton;

    {
        entryBars = new ArrayList<EntryBar>();
        maxSize = 10;
        backButton = new BackButton();
    }

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);

        Random random = new Random();

        addEntry("Bruce Wayne", random.nextInt(1000));
        addEntry("Clark Kent", random.nextInt(1000));
        addEntry("Shayera Hol", random.nextInt(1000));
        addEntry("Tony Stark", random.nextInt(1000));
        addEntry("Steve Rogers", random.nextInt(1000));
        addEntry("T'Challa", random.nextInt(1000));
        addEntry("Peter Parker", random.nextInt(1000));
        addEntry("John Stewart", random.nextInt(1000));
        addEntry("Doctor Strange", random.nextInt(1000));
        addEntry("I am Groot", random.nextInt(1000));
    }

    public boolean isEligibleScore(int score) {
        return ((entryBars.size() < maxSize) ||
                (!entryBars.isEmpty() && (score > entryBars.get(entryBars.size() - 1).getScore())));
    }

    public void addEntry(String name, int score) {

        if (entryBars.size() < maxSize) {
            boolean added = false;
            for (int i = 0; i < entryBars.size(); i++) {
                int curScore = entryBars.get(i).getScore();
                if (score > curScore) {
                    entryBars.add(i, new EntryBar(name, score));
                    added = true;
                    break;
                }
            }
            if (!added) {
                entryBars.add(new EntryBar(name, score));
            }
        } else {
            for (int i = 0; i < entryBars.size(); i++) {
                int curScore = entryBars.get(i).getScore();
                if (score > curScore) {
                    entryBars.add(i, new EntryBar(name, score));
                    break;
                }
            }
            entryBars.remove(maxSize);
        }
    }

    @Override
    protected void addEventHandlers() {

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            } else {
                if (event.getCode() == KeyCode.ESCAPE) {
                    resetMouseVars();
                    windowController.setWindow(Windows.Menu);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            } else {

                double mouseX = event.getX();
                double mouseY = event.getY();

                if (backButton.isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.Menu);
                }
            }
        });
    }

    @Override
    public void show() {

        // Set Entry bars' hovered variable
        for (int rank = 1; rank <= entryBars.size(); rank++) {
            EntryBar entryBar = entryBars.get(rank - 1);
            entryBar.setHovered(entryBar.isHovered(mouseX, mouseY));
        }

        // Set mouse pointer
        if (backButton.isHovered(mouseX, mouseY)) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Back Button
        backButton.show(gc);

        // Leaderboard heading
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 45));
        gc.fillText("Leaderboard", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);

        // Show Entry Bars
        for (int rank = 1; rank <= entryBars.size(); rank++) {
            entryBars.get(rank - 1).show(gc, rank);
        }
    }
}