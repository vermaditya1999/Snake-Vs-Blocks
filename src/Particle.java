import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {
    private Vector pos;
    private Vector vel;
    private Vector acc;

    private double radius;
    private double alpha;

    public Particle(double x, double y) {
        pos = new Vector(x, y);
        vel = Vector.random();
        acc = vel.copy();
        acc.mult(-0.01);
        vel.mult(5);

        radius = 5;
        alpha = 1;
    }

    public boolean isDead() {
        return alpha <= 0 || radius <= 0;
    }

    public void update() {
        vel.add(acc);
        pos.add(vel);

        radius -= 0.25;
        alpha -= 0.025;
    }

    public void show(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, alpha));
        gc.fillOval(pos.x, pos.y, radius * 2, radius * 2);
    }
}
