package com.katas.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class PlayerTest {

    @Test
    public void testPlayerInstantiation() {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 2");
        Player player = new Player(grid, playerPosition);
        assertEquals(player.getPosition().toString(), "2 2");
    }

    // TODO you can move Position.createPosition a level up
    // TODO this way you don't need to pass grid both ways
    
    @Test
    public void testPlayerFindsEndPositionWithoutAnyWall() throws Exception {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, initialPosition);
        Player player = new Player(grid, playerPosition);
        assertEquals(endPosition, player.findEndPosition());
    }

}
