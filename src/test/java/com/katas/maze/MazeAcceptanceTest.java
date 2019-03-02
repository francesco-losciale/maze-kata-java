package com.katas.maze;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MazeAcceptanceTest {

    @Test
    public void testSimpleInput() throws Exception {
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
        SearchStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, strategy);
        player.find(playerPosition, endPosition);
        for (Position position : strategy.getVisitedPositionList()) {
            if (!position.equals(playerPosition))
                grid.markCellUsed(position);
        }
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

    @Test
    public void testAcceptanceInputChooseTwoPath() throws Exception {
        String resourceName = "input_two_ways.txt";
        MazeFileReader mazeFileReader = new MazeFileReader(resourceName);
        String expectedGridOutput = "# # # # # # #\n" +
                                    "# # # # # # #\n" +
                                    "# S 0 0 0 0 #\n" +
                                    "# X 0 0 0 0 #\n" +
                                    "# X # # # # #\n" +
                                    "# X # # # # #\n" +
                                    "# X X X X E #";
        Grid grid = new Grid(mazeFileReader);
        Position playerPosition = Position.createPosition(grid, mazeFileReader.getInitialPosition());
        Position endPosition = Position.createPosition(grid, mazeFileReader.getEndPosition());
        SearchStrategy searchStrategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, searchStrategy);
        player.find(playerPosition, endPosition);
        for (Position position : searchStrategy.getVisitedPositionList()) {
            if (!position.equals(playerPosition))
                grid.markCellUsed(position);
        }
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
        TryAllPossibleDirectionsStrategy searchStrategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, searchStrategy);
        player.find(playerPosition, endPosition);
        for (Position position : searchStrategy.getVisitedPositionList()) {
            if (!position.equals(playerPosition))
                grid.markCellUsed(position);
        }
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

}
