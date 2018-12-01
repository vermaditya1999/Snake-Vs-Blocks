package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Image;
import com.snakevsblocks.util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coin extends Token {

    public Coin(int x, int y) {
        super(x, y);
    }

    public void update(double speed) {
        pos.y += speed;
    }

    public void attract(Vector pos) {
        this.pos.lerp(pos, 0.25);
    }

    @Override
    public void show(GraphicsContext gc) {
        if (Image.getCOIN() == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(Image.getCOIN(), pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
