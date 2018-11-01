import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EntryBar {

    public static final double RADIUS = Game.TILE_SIZE / 5;

    private Vector pos;
    private double height;
    private double width;
    private String name;
    private int score;
    private boolean hovered;

    {
        pos = new Vector(Game.SCREEN_WIDTH / 2, 0);
        height = EntryBar.RADIUS * 2;
        width = Game.TILE_SIZE * 3.5;
        hovered = false;
    }

    public EntryBar(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void show(GraphicsContext gc, int rank) {

        // Set font
        gc.setFont(new Font("Consolas", 20));

        // Set y coordinate of pos (Subject to rank)
        pos.y = Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60;

        // Show hover bar
        if (hovered) {
            gc.setFill(Color.WHITE);
            gc.fillRect(pos.x - width / 2 + EntryBar.RADIUS, pos.y - height / 2, width, height);
        }

        // Show rank label
        gc.setFill(Color.WHITE);
        gc.fillOval(Game.TILE_SIZE - EntryBar.RADIUS, pos.y - EntryBar.RADIUS, EntryBar.RADIUS * 2, EntryBar.RADIUS * 2);

        // Show rank
        gc.setFill(LeaderBoard.BG_COLOR);
        gc.fillText(Integer.toString(rank), Game.TILE_SIZE , Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60);

        // Set fill color for text
        if (hovered) {
            gc.setFill(LeaderBoard.BG_COLOR);
        } else {
            gc.setFill(Color.WHITE);
        }

        // Show name and score
        gc.fillText(name, pos.x, pos.y);
        gc.fillText(Integer.toString(score), pos.x + Game.TILE_SIZE * 1.5, pos.y);
    }

    public int getScore() {
        return score;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return Math.abs(mouseX - pos.x) <= width / 2 && Math.abs(mouseY - pos.y) <= height / 2;
    }
}