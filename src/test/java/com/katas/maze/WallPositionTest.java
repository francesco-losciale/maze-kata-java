package com.katas.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WallPositionTest {

    @Test
    public void testWallPositionInstantiation() throws Exception {
        WallPosition wallPosition = new WallPosition(5, 7);
        assertEquals(5, wallPosition.getX());
        assertEquals(7, wallPosition.getY());
    }

    @Test
    public void testCheckIfPositionIsWall() throws Exception {
        WallPosition wallPosition = new WallPosition(5, 5);
        Grid grid = new Grid("9 9");
        Position position = Position.createInitialPosition(grid, "5 5");
        assertTrue(wallPosition.equals(position));
    }
}
