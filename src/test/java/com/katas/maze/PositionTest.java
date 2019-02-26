package com.katas.maze;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test
    public void testPositionInstantiation() {
        Position position = new Position("0 0", "2 2");
        assertEquals(position.getX(), 0);
        assertEquals(position.getY(), 0);
    }
}
