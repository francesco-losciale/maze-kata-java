package com.katas.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testPlayerInstantiation() {
        Position playerPosition = new Position("0 0", "2 2");
        Grid grid = new Grid("2 2");
        Player player = new Player(grid, playerPosition);
        assertEquals(player.getPosition().toString(), "0 0");
    }

}
