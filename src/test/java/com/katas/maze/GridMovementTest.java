package com.katas.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridMovementTest {
    
    @Test
    public void testBasicGridMovementWithoutAnyWallWithOutput() {
        Character instruction = 'E';
        String currentPosition = "0 0";
        String endPosition = "1 0";
        Grid grid = new Grid("2 2", "0 0");
        Player player = new Player(currentPosition, grid);
        assertEquals(player.executeMove(instruction), endPosition);
        String expectedGridOutput = "S X\n" +
                                    "0 0\n";
        assertEquals(expectedGridOutput, grid.toString());
    }

}
