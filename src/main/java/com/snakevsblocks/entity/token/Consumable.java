package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Vector;

public interface Consumable {
    void consume(Vector snakeHeadVector);

    boolean isConsumed();
}
