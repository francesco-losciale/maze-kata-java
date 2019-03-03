package com.katas.maze;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GridTest {

    @Test
    public void testGridInitialization() throws Exception {
        String resourceName = "input.txt";
        MazeFileReader mazeFileReader = new MazeFileReader(resourceName);
        String expectedGridOutput = "# # # # #\n" +
                                    "# S # 0 #\n" +
                                    "# 0 # 0 #\n" +
                                    "# 0 0 E #\n" +
                                    "# # # # #";
        Grid grid = new Grid(mazeFileReader);
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

    @Test
    public void testGridPlateauLimits() {
        assertEquals(new Grid("5 1").getLimitX(), 5);
        assertEquals(new Grid("1 5").getLimitY(), 5);
    }

    @Test
    public void testGridPlateauLargeLimits() {
        assertEquals(new Grid("10 15").getLimitX(), 10);
        assertEquals(new Grid("10 15").getLimitY(), 15);
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
        Position playerPosition = Position.createPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.movePlayer(instruction);
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
        Position playerPosition = Position.createPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.movePlayer(instruction);
        assertEquals(endPosition, player.getPosition().toString());
        assertEquals(expectedGridOutput, grid.toString().trim());
    }

    @Test
    public void testMovementAgainstWallDoesNotChangeGrid() {
        final String initialGrid = "S 0\n0 0\n";
        final String currentPosition = "0 0";
        final String plateauCoordinates = "2 2";
        final Character instruction = 'E';
        Grid grid = new Grid(plateauCoordinates);
        grid.addWallAtCoordinates("1 0");
        Position playerPosition = Position.createPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        player.movePlayer(instruction);
        assertEquals(initialGrid.trim(), grid.toString().trim());
    }
}
