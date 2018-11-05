import javafx.scene.canvas.GraphicsContext;

public abstract class Token implements Collideable {

    public final static double RADIUS = 10;

    public Vector pos;

    public boolean dead;

    public Token(double x, double y) {

        pos = new Vector((x - 1) * App.TILE_SIZE + App.TILE_SIZE / 2, (y - 1) * App.TILE_SIZE + App.TILE_SIZE / 2);
        dead = false;
    }

    public abstract void show(GraphicsContext gc);

    public void update(double speed) {
        pos.y += speed;
    }

    public boolean isOver() {
        return dead || (pos.y - Token.RADIUS >= App.SCREEN_HEIGHT);
    }

    @Override
    public void collide(Vector snakeHeadVector) {
        dead = Vector.dist(snakeHeadVector, pos) <= (SnakeBall.RADIUS + Token.RADIUS);
    }
}
