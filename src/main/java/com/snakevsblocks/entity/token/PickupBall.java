package com.snakevsblocks.entity.token;

import com.snakevsblocks.entity.SnakeBall;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * PickupBall is a Token which, when consumed, increases the
 * length of the snake by its value.
 *
 * @see Token
 * <p>
 * The value of the PickupBall is set as per the following
 * Probability chart:
 * 1 : 50%
 * 2 : 30%
 * 3 : 10%
 * 4 : 6%
 * 5 : 4%
 */
public class PickupBall extends Token {

    /**
     * Value of the ball.
     */
    private int value;

    /**
     * Creates new PickupBall.
     * @param x x coordinate of the PickupBall centre
     * @param y y coordinate of the PickupBall centre
     */
    public PickupBall(double x, double y) {

        super(x, y);
        assignValue();
    }

    /**
     * Assigns value to the Ball as per the probability chart.
     */
    private void assignValue() {

        int choose = Random.nextInt(100);
        if (choose < 50) {
            value = 1;
        } else if (choose < 80) {
            value = 2;
        } else if (choose < 90) {
            value = 3;
        } else if (choose < 96) {
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
