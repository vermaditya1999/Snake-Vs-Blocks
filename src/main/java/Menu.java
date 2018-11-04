import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Menu extends Window {

    public static final Color BG_COLOR = Color.rgb(245, 245, 245);
    public static final Color FG_COLOR = Color.rgb(60, 60, 60);

    private enum MenuButtons {
        ResumeGame,
        StartGame,
        Leaderboard,
        Exit
    }

    // ArrayList to hold all menu buttons
    private HashMap<MenuButtons, MenuButton> menuButtons;

    // Boolean variable true if a saved game is available
    private boolean savedGame;

    // Score of the previous score
    private int prevScore;

    // Info button
    private InfoButton infoButton;

    public Menu(WindowController wc, Group root) {
        super(wc, root);

        // Set temporary value for demonstration
        prevScore = 641;

        // Set temporary value for demonstration
        savedGame = false;

        // Initialize the menuButtons HashMap
        menuButtons = new HashMap<MenuButtons, MenuButton>();

        // Add menu buttons
        addMenuButtons();

        // Add info button
        infoButton = new InfoButton();
    }

    private void addMenuButtons() {

        double gap = 0.75;
        double offset = gap * Game.TILE_SIZE;

        // Add resume button only if there is a saved game
        if (savedGame) {
            menuButtons.put(MenuButtons.ResumeGame, new MenuButton("Resume Game", Game.SCREEN_HEIGHT / 2 + gap * Game.TILE_SIZE));
        } else {
            offset = 0;  // No Resume Game button, set offset to 0
        }

        // Add rest of the buttons
        menuButtons.put(MenuButtons.StartGame, new MenuButton("Start Game", Game.SCREEN_HEIGHT / 2 + gap * Game.TILE_SIZE + offset));
        menuButtons.put(MenuButtons.Leaderboard, new MenuButton("Leaderboard", Game.SCREEN_HEIGHT / 2 + 2 * gap * Game.TILE_SIZE + offset));
        menuButtons.put(MenuButtons.Exit, new MenuButton("Exit", Game.SCREEN_HEIGHT / 2 + 3 * gap * Game.TILE_SIZE + offset));
    }

    @Override
    protected void addEventHandlers() {

        addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            Windows currentWindow = windowController.currentWindow();

            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            } else {

                if (menuButtons.get(MenuButtons.Leaderboard).isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.LeaderBoard);
                }

                if (menuButtons.get(MenuButtons.StartGame).isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    windowController.setWindow(Windows.GamePlay);
                }

                if (menuButtons.get(MenuButtons.Exit).isHovered(mouseX, mouseY)) {
                    resetMouseVars();
                    Platform.exit();
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_MOVED, event -> {

            Windows currentWindow = windowController.currentWindow();
            if (currentWindow != Windows.Menu) {
                windowController.passEvent(currentWindow, event);
            } else {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    @Override
    public void show() {

        // Set hovered variable
        boolean hovered = false;
        Iterator entries = menuButtons.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            MenuButton menuButton = (MenuButton) entry.getValue();
            if (menuButton.isHovered(mouseX, mouseY)) {
                hovered = true;
            }
        }
        if (infoButton.isHovered(mouseX, mouseY)) {
            hovered = true;
        }

        // Set cursor
        if (hovered) {
            setCursor(Cursor.HAND);
        } else {
            setCursor(Cursor.DEFAULT);
        }

        // Set background
        gc.setFill(Menu.BG_COLOR);
        gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);


        // Show game title
        gc.setFont(Fonts.GOTHAM_LARGE);
        gc.setFill(Menu.FG_COLOR);
        gc.fillText("Snake", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE);
        gc.fillText("vs", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.75);
        gc.fillText("Blocks", Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 2.5);

        // Show previous score
        gc.setFont(Fonts.MUSEO);
        gc.setFill(Menu.FG_COLOR);
        gc.fillText(Integer.toString(prevScore), Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 3);

        // Show the horizontal rule, how to change its length with width of the prevScore?
        gc.setLineWidth(2.0);
        gc.setStroke(Menu.FG_COLOR);
        gc.beginPath();
        gc.moveTo(0, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 3);
        gc.lineTo(Game.TILE_SIZE * 1.5, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 3);
        gc.moveTo(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 3);
        gc.lineTo(Game.SCREEN_WIDTH - Game.TILE_SIZE * 1.5, Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 3);
        gc.stroke();
        gc.closePath();

        // Show Menu buttons
        entries = menuButtons.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            MenuButton menuButton = (MenuButton) entry.getValue();
            menuButton.show(gc);
        }

        // Show Info button
        infoButton.show(gc);
    }
}