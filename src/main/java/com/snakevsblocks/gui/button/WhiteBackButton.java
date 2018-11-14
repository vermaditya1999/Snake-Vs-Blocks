package com.snakevsblocks.gui.button;

import com.snakevsblocks.util.Image;
import javafx.scene.canvas.GraphicsContext;

public class WhiteBackButton extends BackButton {

    public WhiteBackButton(double x, double y) {
        super(x, y);
    }

    @Override
    public void show(GraphicsContext gc) {
        gc.drawImage(Image.getWBack(), pos.x - size / 2, pos.y - size / 2, size, size);
    }
}
