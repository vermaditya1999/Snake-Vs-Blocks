import javafx.scene.canvas.GraphicsContext;

public abstract class Token {

    protected float radius = 20;

    protected Vector pos;

    public Token (double x, double y){

        pos = new Vector(x, y);
    }

    public abstract void show(GraphicsContext gc);

}
