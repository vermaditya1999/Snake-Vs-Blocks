import javafx.event.Event;
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

    private ArrayList<EntryBar> entryList;
    private int maxSize;

    private BackButton backBtn;

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);

        entryList = new ArrayList<EntryBar>();
        backBtn = new BackButton();
        maxSize = 10;

        Random random = new Random();

        addEntry("Bruce Wayne", random.nextInt(1000));
        addEntry("Clark Kent", random.nextInt(1000));
        addEntry("Shayera Hol", random.nextInt(1000));
        addEntry("Tony Stark", random.nextInt(1000));
        addEntry("Steve Rogers", random.nextInt(1000));
        addEntry("T'Challa", random.nextInt(1000));
        addEntry("Peter Parker", random.nextInt(1000));
        addEntry("John Stewart", random.nextInt(1000));
    }

    public boolean isEligibleScore(int score) {
        return ((entryList.size() < maxSize) ||
               (!entryList.isEmpty() && (score > entryList.get(entryList.size() - 1).getScore())));
    }

    public void addEntry(String name, int score) {

        if (entryList.size() < maxSize) {
            boolean added = false;
            for (int i = 0; i < entryList.size(); i++) {
                int curScore = entryList.get(i).getScore();
                if (score > curScore) {
                    entryList.add(i, new EntryBar(name, score));
                    added = true;
                    break;
                }
            }
            if (!added) {
                entryList.add(new EntryBar(name, score));
            }
        } else {
            for (int i = 0; i < entryList.size(); i++) {
                int curScore = entryList.get(i).getScore();
                if (score > curScore) {
                    entryList.add(i, new EntryBar(name, score));
                    break;
                }
            }
            entryList.remove(maxSize);
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
                    windowController.setWindow(Windows.Menu);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            } else {

                double mouseX = event.getX();
                double mouseY = event.getY();

                if (backBtn.isHovered(mouseX, mouseY)) {
                    canvas.setCursor(Cursor.HAND);
                } else {
                    canvas.setCursor(Cursor.DEFAULT);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.LeaderBoard) {
                windowController.passEvent(currentWindow, event);
            } else {

                double mouseX = event.getX();
                double mouseY = event.getY();

                if (backBtn.isHovered(mouseX, mouseY)) {
                    windowController.setWindow(Windows.Menu);
                }
            }
        });
    }

    @Override
    public void show() {

        // Set background
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // Back Button
        backBtn.show(gc);

        // Leaderboard heading
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 45));
        gc.fillText("Leaderboard", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 0.80);

        // Show Entry Bars
        for (int rank = 1; rank <= entryList.size(); rank++) {
            entryList.get(rank - 1).show(gc, rank);
        }
    }

    @Override
    public void fireEvent(Event event) {
        canvas.fireEvent(event.copyFor(canvas, canvas));
    }
}