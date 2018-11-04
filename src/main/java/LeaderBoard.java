import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class LeaderBoard extends Window {

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    private ArrayList<EntryPane> entryPanes;
    private int maxSize;
    private BackButton backButton;

    {
        entryPanes = new ArrayList<EntryPane>();
        maxSize = 10;
        backButton = new BackButton();
    }

    public LeaderBoard(WindowController wc, Group root) {
        super(wc, root);

        Random random = new Random();

        addEntry("23-08-2018", random.nextInt(1000));
        addEntry("12-07-2011", random.nextInt(1000));
        addEntry("13-05-3012", random.nextInt(1000));
        addEntry("16-12-2018", random.nextInt(1000));
        addEntry("21-04-2000", random.nextInt(1000));
        addEntry("29-05-1999", random.nextInt(1000));
        addEntry("30-08-1856", random.nextInt(1000));
        addEntry("01-07-2000", random.nextInt(1000));
        addEntry("31-11-2018", random.nextInt(1000));
        addEntry("06-01-2011", random.nextInt(1000));
    }

    public boolean isEligibleScore(int score) {
        return ((entryPanes.size() < maxSize) ||
                (!entryPanes.isEmpty() && (score > entryPanes.get(entryPanes.size() - 1).getScore())));
    }

    public void addEntry(String name, int score) {

        if (entryPanes.size() < maxSize) {
            boolean added = false;
            for (int i = 0; i < entryPanes.size(); i++) {
                int curScore = entryPanes.get(i).getScore();
                if (score > curScore) {
                    entryPanes.add(i, new EntryPane(name, score));
                    added = true;
                    break;
                }
            }
            if (!added) {
                entryPanes.add(new EntryPane(name, score));
            }
        } else {
            for (int i = 0; i < entryPanes.size(); i++) {
                int curScore = entryPanes.get(i).getScore();
                if (score > curScore) {
                    entryPanes.add(i, new EntryPane(name, score));
                    break;
                }
            }
            entryPanes.remove(maxSize);
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
        for (int rank = 1; rank <= entryPanes.size(); rank++) {
            EntryPane entryPane = entryPanes.get(rank - 1);
            entryPane.setHovered(entryPane.isHovered(mouseX, mouseY));
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
        backButton.show(gc, LeaderBoard.FG_COLOR);

        // Leaderboard heading
        gc.setFill(LeaderBoard.FG_COLOR);
        gc.setFont(Fonts.GOTHAM_MEDIUM);
        gc.fillText("Leaderboard", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);

        // Show Entry Bars
        for (int rank = 1; rank <= entryPanes.size(); rank++) {
            entryPanes.get(rank - 1).show(gc, rank);
        }
    }
}