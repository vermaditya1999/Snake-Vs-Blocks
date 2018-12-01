package com.snakevsblocks.gui;

import com.snakevsblocks.App;
import com.snakevsblocks.entity.token.Token;
import com.snakevsblocks.util.Image;
import com.snakevsblocks.window.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MagnetTimer extends Timer {

    public MagnetTimer() {
        super(App.SCREEN_WIDTH / 2 - App.TILE_SIZE / 2, App.TILE_SIZE / 2);
    }

    @Override
    public void run(GraphicsContext gc) {
        val--;
        gc.drawImage(Image.getMAGNET(), pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
        gc.setFill(new Color(Game.BG_COLOR.getRed(), Game.BG_COLOR.getBlue(), Game.BG_COLOR.getGreen(), 1 - (val / Timer.MAX_VAL)));
        gc.fillOval(pos.x - Token.RADIUS, pos.y - Token.RADIUS, 2 * Token.RADIUS, 2 * Token.RADIUS);
    }
}
