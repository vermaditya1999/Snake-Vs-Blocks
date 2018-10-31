import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EntryBar {

    private String name;

    private int score;


    public EntryBar(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void show(GraphicsContext gc, int rank) {

        double r = Game.TILE_SIZE / 5;

        gc.setFill(Color.rgb(232,74,95));
        gc.fillOval(Game.TILE_SIZE - r, Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60 - r, r * 2, r * 2);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Consolas", 20));
        gc.fillText(Integer.toString(rank), Game.TILE_SIZE , Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60);

        gc.fillText(name, Game.SCREEN_WIDTH / 2, Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60);
        gc.fillText(Integer.toString(score), Game.SCREEN_WIDTH - Game.TILE_SIZE, Game.TILE_SIZE * 1.25 + (Game.TILE_SIZE * rank) * 0.60);
    }

    public int getScore() {
        return score;
    }
}
