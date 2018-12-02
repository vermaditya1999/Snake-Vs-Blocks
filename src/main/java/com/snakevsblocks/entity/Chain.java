package com.snakevsblocks.entity;

import java.util.ArrayList;

/**
 * Chain is a row of five blocks.
 *
 * @see Block
 */
public class Chain {

    /**
     * List of blocks in a row.
     */
    private ArrayList<Block> blockRow;

    /**
     * Creates a new Chain.
     *
     * @param snakeLength Length of the snake
     */
    public Chain(int snakeLength) {
        blockRow = new ArrayList<>(5);

        addBlocks(snakeLength);
    }

    /**
     * Adds blocks in the chain, with the condition that
     * length of snake cannot be less than/equal to all
     * the blocks in the chain.
     *
     * @param snakeLength Length of the snake
     */
    private void addBlocks(int snakeLength) {

        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 1; i <= 5; i++) {
            Block block = new Block(i, -2, snakeLength);
            blockRow.add(block);

            if (minValue > block.getValue()) {
                minValue = block.getValue();
                minIndex = i - 1;
            }
        }

        if (minValue >= snakeLength) {
            blockRow.get(minIndex).setValue(snakeLength/2);

            if (snakeLength == 1) {
                blockRow.set(minIndex, null);
            }
        }
    }

    public ArrayList<Block> getBlockRow() {
        return blockRow;
    }
}
