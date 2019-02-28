package com.katas.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testPlayerInstantiation() {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 2");
        Player player = new Player(grid, playerPosition);
        assertEquals(player.getPosition().toString(), "2 2");
    }
    
    @Test
    public void testWhereCanPlayerGo() throws Exception {
        
    }

}
