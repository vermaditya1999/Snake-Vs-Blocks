import javafx.scene.canvas.GraphicsContext;

public abstract class Token {

    protected float radius;

    protected Vector pos;

    {
        radius = 10;
    }

    public Token (double x, double y){

        pos = new Vector((x - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2, (y - 1) * Game.TILE_SIZE + Game.TILE_SIZE / 2);
    }

    public abstract void show(GraphicsContext gc);

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isOver() {
        return pos.y - radius >= Game.SCREEN_HEIGHT;
    }
}
