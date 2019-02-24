package com.katas.maze;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GridMovementTest {
    
    @Test
    @Parameters({
            "E, 0 0, 1 0, 2 2, S X\n0 0\n",
            "W, 1 0, 0 0, 2 2, X S\n0 0\n",
            "N, 0 1, 0 0, 2 2, X 0\nS 0\n",
            "S, 0 0, 0 1, 2 2, S 0\nX 0\n"
    })
    public void testMovementWithoutAnyWall(Character instruction,
                                           String currentPosition,
                                           String endPosition,
                                           String plateauCoordinates,
                                           String expectedGridOutput) {
        Position playerPosition = new Position(currentPosition);
        Grid grid = new Grid(plateauCoordinates);
        Player player = new Player(grid, playerPosition);
        player.executeInstruction(instruction);
        assertEquals(endPosition, player.getPosition().toString());
        assertEquals(expectedGridOutput, grid.toString().trim());
    }

//    @Test
//    @Parameters({
//            "W, 0 0, 1 0, 2 2, S X\n0 0\n",
//    })
//    public void testWrapperMovementWithoutAnyWall(Character instruction,
//                                           String currentPosition,
//                                           String endPosition,
//                                           String plateauCoordinates,
//                                           String expectedGridOutput) {
//        Position playerPosition = new Position(currentPosition);
//        Grid grid = new Grid(plateauCoordinates, playerPosition);
//        Player player = new Player(grid, playerPosition);
//        player.executeInstruction(instruction);
//        assertEquals(endPosition, player.getPosition().toString());
//        assertEquals(expectedGridOutput, grid.toString().trim());
//    }

}
