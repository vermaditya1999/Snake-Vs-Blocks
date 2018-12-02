package com.snakevsblocks.entity;

import java.util.ArrayList;
import java.util.Collections;

public class Chain {

    private ArrayList<Block> blockRow;

    public Chain(int snakeLength) {
        blockRow = new ArrayList<>(5);

        addBlocks(snakeLength);
    }

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
