import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Particle {

    private static Random random = new Random();

    private Vector pos;
    private Vector vel;
    private Vector acc;

    private double radius;
    private double alpha;

    private int r;
    private int g;
    private int b;

    public Particle(double x, double y) {
        pos = new Vector(x, y);
        vel = Vector.random();
        acc = vel.copy();
        acc.mult(-0.01);
        vel.mult(5);

        radius = 6;
        alpha = 1;

        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);
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
//        gc.setFill(Color.rgb(r, g, b, alpha));
        gc.setFill(Color.rgb(255, 255, 255, alpha));
        gc.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
    }
}
