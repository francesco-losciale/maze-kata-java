package com.katas.maze;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class PositionTest {

    @Test
    public void testPositionInstantiation() {
        final String plateauCoordinates = "2 2";
        final Grid grid = new Grid(plateauCoordinates);
        final Position position = Position.createInitialPosition(grid, "0 0");
        assertEquals(position.getX(), 0);
        assertEquals(position.getY(), 0);
    }

    @Test
    public void testPositionDoesNotChangeWhenAgainstWall() throws Exception {
        final String currentPosition = "0 0";
        final String plateauCoordinates = "2 2";
        final Character instruction = 'E';
        final String wallCellPosition = "1 0";
        Grid grid = new Grid(plateauCoordinates, wallCellPosition);
        Position playerPosition = Position.createInitialPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        try {
            player.executeInstruction(instruction);
        } catch (Exception e) {
            assertEquals(currentPosition.toString(), playerPosition.toString());
            return;
        }
        fail("Expected Exception \"Wall found\" executing instruction");
    }

    @Test
    public void testPositionIsImmutable() throws Exception {
        final String plateauCoordinates = "2 2";
        final Grid grid = new Grid(plateauCoordinates);
        final Position position = Position.createInitialPosition(grid, "0 0");
        assertNotEquals(position, position.moveEast());
        assertNotEquals(position, position.moveWest());
        assertNotEquals(position, position.moveNorth());
        assertNotEquals(position, position.moveSouth());

    }
}
