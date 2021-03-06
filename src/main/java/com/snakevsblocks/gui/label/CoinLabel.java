package com.snakevsblocks.gui.label;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Font;
import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * This class is used to create a Coin Label.
 */
public class CoinLabel extends Label {

    /**
     * Create a new Coin Label.
     */
    public CoinLabel() {
        super(App.SCREEN_WIDTH - App.TILE_SIZE / 2, App.TILE_SIZE / 2);
    }

    @Override
    public void show(GraphicsContext gc) {

        gc.setTextAlign(TextAlignment.RIGHT);

        gc.setFont(Font.CONSOLAS_MEDIUM);
        gc.setFill(Color.WHITE);
        gc.fillText(text, pos.x - Token.RADIUS - App.TILE_SIZE / 4, pos.y + 1);

        gc.setTextAlign(TextAlignment.CENTER);

        if (Image.getCOIN() == null) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        } else {
            gc.drawImage(Image.getCOIN(), pos.x - Token.RADIUS,
                    pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        }
    }
}
