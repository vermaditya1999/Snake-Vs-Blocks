import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Burst {
    private ArrayList<Particle> particles;
    private Vector origin;
    private int maxNum;
    private int curNum;

    public Burst(double x, double y) {
        particles = new ArrayList<Particle>();
        origin = new Vector(x, y);
        maxNum = 15;
        curNum = 0;

        particles.add(new Particle(x, y));
    }

    public boolean isOver() {
        return particles.size() == 0;
    }

    public void show(GraphicsContext gc) {
        if (curNum < maxNum) {
            particles.add(new Particle(origin.x, origin.y));
            curNum++;
        }

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);

            p.update();
            if (p.isDead()) {
                particles.remove(i);
            } else {
                p.show(gc);
            }
        }
    }
}
