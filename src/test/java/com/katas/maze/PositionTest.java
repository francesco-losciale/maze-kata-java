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
        final Position position = Position.createPosition(grid, "0 0");
        assertEquals(position.getX(), 0);
        assertEquals(position.getY(), 0);
    }

    @Test
    public void testPositionDoesNotChangeWhenAgainstWall() {
        final String currentPosition = "0 0";
        final String plateauCoordinates = "2 2";
        final Character instruction = 'E';
        Grid grid = new Grid(plateauCoordinates).addWallAtCoordinates("1 0");
        Position playerPosition = Position.createPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.movePlayer(instruction);
        assertEquals("Position hasn't changed", playerPosition, player.getPosition());
    }

    @Test
    public void testPositionIsImmutable() throws Exception {
        final String plateauCoordinates = "2 2";
        final Grid grid = new Grid(plateauCoordinates);
        final Position position = Position.createPosition(grid, "0 0");
        assertNotEquals(position, position.moveEast());
        assertNotEquals(position, position.moveWest());
        assertNotEquals(position, position.moveNorth());
        assertNotEquals(position, position.moveSouth());

    }
}
