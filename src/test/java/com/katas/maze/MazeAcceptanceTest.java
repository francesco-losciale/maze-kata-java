package com.katas.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MazeAcceptanceTest {

    @Test
    public void testAcceptanceInput() throws Exception {
        String resourceName = "input.txt";
        MazeFileReader mazeFileReader = new MazeFileReader(resourceName);
        String expectedGridOutput = "# # # # #\n" +
                                    "# S # 0 #\n" +
                                    "# X # 0 #\n" +
                                    "# X X E #\n" +
                                    "# # # # #";
        Grid grid = new Grid(mazeFileReader);
        Position playerPosition = Position.createPosition(grid, mazeFileReader.getInitialPosition());
        Position endPosition = Position.createPosition(grid, mazeFileReader.getEndPosition());
        Player player = new Player(grid, playerPosition, new TryAllPossibleDirectionsStrategy(playerPosition));
        player.find(playerPosition, endPosition);
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

    @Test
    public void testLargeInput() throws Exception {
        String resourceName = "large_input.txt";
        MazeFileReader mazeFileReader = new MazeFileReader(resourceName);
        String expectedGridOutput = AcceptanceOutputExpectation.largeInput;
        Grid grid = new Grid(mazeFileReader);
        Position playerPosition = Position.createPosition(grid, mazeFileReader.getInitialPosition());
        Position endPosition = Position.createPosition(grid, mazeFileReader.getEndPosition());
        Player player = new Player(grid, playerPosition, new TryAllPossibleDirectionsStrategy(playerPosition));
        player.find(playerPosition, endPosition);
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

}
