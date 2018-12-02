package com.snakevsblocks.entity.token;

import com.snakevsblocks.util.Vector;

/**
 * Consumable interface allows the class object to implement
 * the consumption of one object by the other, by checking their
 * respective vicinities with each other.
 */
public interface Consumable {

    /**
     * Allows the snake to consume the token.
     *
     * @param snakeHeadVector the head position vector of the snake
     */
    void consume(Vector snakeHeadVector);

    /**
     * Provides the information of whether the object is consumed.
     * @return true if object is consumed
     */
    boolean isConsumed();
}
