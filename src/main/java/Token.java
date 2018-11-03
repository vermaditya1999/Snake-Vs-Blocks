import javafx.scene.canvas.GraphicsContext;

public abstract class Token {

    public final static double RADIUS = 10;

    public Vector pos;

    public Token(double x, double y) {

        pos = new Vector((x - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2, (y - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2);
    }

    public abstract void show(GraphicsContext gc);

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isOver() {
        return pos.y - Token.RADIUS >= Game.SCREEN_HEIGHT;
    }

}
