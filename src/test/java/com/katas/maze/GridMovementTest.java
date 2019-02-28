package com.katas.maze;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class GridMovementTest {

    // TODO test grid plateau 10 10

    @Test
    public void testGridPlateauLimits() throws Exception {
        assertEquals(new Grid("5 1", "0 0").getLimitX(), 5);
        assertEquals(new Grid("1 5", "0 0").getLimitY(), 5);
    }

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
        Grid grid = new Grid(plateauCoordinates);
        Position playerPosition = Position.createInitialPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.executeInstruction(instruction);
        assertEquals(endPosition, player.getPosition().toString());
        assertEquals(expectedGridOutput, grid.toString().trim());
    }

    @Test
    @Parameters({
            "W, 0 0, 1 0, 2 2, S X\n0 0\n",
            "E, 1 0, 0 0, 2 2, X S\n0 0\n",
            "S, 0 1, 0 0, 2 2, X 0\nS 0\n",
            "N, 0 0, 0 1, 2 2, S 0\nX 0\n",
    })
    public void testWrapperMovementWithoutAnyWall(Character instruction,
                                           String currentPosition,
                                           String endPosition,
                                           String plateauCoordinates,
                                           String expectedGridOutput) {
        Grid grid = new Grid(plateauCoordinates);
        Position playerPosition = Position.createInitialPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.executeInstruction(instruction);
        assertEquals(endPosition, player.getPosition().toString());
        assertEquals(expectedGridOutput, grid.toString().trim());
    }

    @Test
    public void testGridInstantiationWithWall() throws Exception {
        List<WallPosition> expectedWallPositionList = new ArrayList<WallPosition>();
        Grid grid = new Grid("5 5", expectedWallPositionList);
        assertEquals(expectedWallPositionList, grid.getWallPositionList());
    }

    @Test(expected = RuntimeException.class)
    public void testMovementExceptionWhenWallFound() {
        final String currentPosition = "0 0";
        final String plateauCoordinates = "2 2";
        final Character instruction = 'E';
        final String wallCellPosition = "1 0";
        Grid grid = new Grid(plateauCoordinates, wallCellPosition);
        Position playerPosition = Position.createInitialPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.executeInstruction(instruction);
    }

    @Test
    public void testMovementAgainstWallDoesNotChangeGrid() throws Exception {
        final String initialGrid = "S 0\n0 0\n";
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
            assertEquals(initialGrid.trim(), grid.toString().trim());
        }
    }
}
