package com.snakevsblocks.entity.token;

import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PickupBall extends Token {

    private int value;

    public PickupBall(double x, double y) {

        super(x, y);
        assignValue();
    }

    private void assignValue() {
        /* Probability chart for Ball value:
         * 1 : 60%
         * 2 : 30%
         * 3 : 5%
         * 4 : 3%
         * 5 : 2%
         */
        int choose = Random.nextInt(100);
        if (choose < 60) {
            value = 1;
        } else if (choose < 90) {
            value = 2;
        } else if (choose < 95) {
            value = 3;
        } else if (choose < 98) {
            value = 4;
        } else {
            value = 5;
        }

    }

    public int getValue() {
        return value;
    }

    @Override
    public void show(GraphicsContext gc) {

        // Show value
        gc.setFont(Font.CONSOLAS_XSMALL);
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(value), pos.x, pos.y - SnakeBall.RADIUS - 8);

        gc.setFill(Color.rgb(SnakeBall.COLOR[0], SnakeBall.COLOR[1], SnakeBall.COLOR[2]));
        gc.fillOval(pos.x - SnakeBall.RADIUS, pos.y - SnakeBall.RADIUS, 2 * SnakeBall.RADIUS, 2 * SnakeBall.RADIUS);  // Use SnakeBall Radius instead of Token Radius
    }
}
