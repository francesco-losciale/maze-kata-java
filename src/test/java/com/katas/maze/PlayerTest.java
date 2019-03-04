package com.katas.maze;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
@RunWith(JUnitParamsRunner.class)
public class PlayerTest {

    @Test
    public void testPlayerInstantiation() {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 2");
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, strategy);
        assertEquals(player.getPosition().toString(), "2 2");
    }

    @Test
    @Parameters({"0 0, 0 0, 3 3",
                 "0 0, 0 1, 3 3",
                 "0 0, 0 2, 3 3",
                 "0 0, 1 0, 3 3",
                 "0 0, 2 0, 3 3",
                 "0 0, 1 1, 3 3"})
    public void testPlayerFindsEndPositionWithoutAnyWall(
            String initialPosition,
            String endPosition,
            String gridPlateauCoordinates
    ) throws Exception {
        Grid grid = new Grid(gridPlateauCoordinates);
        Position playerPosition = Position.createPosition(grid, initialPosition);
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<>();
        assertTrue(player.find(playerPosition, Position.createPosition(grid, endPosition), visitedPositionList));
    }

    @Test
    public void testPlayerFindsEndPositionAndPathIsWhatExpectedWithoutAnyWall() {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        final String plateauCoordinates = "3 3";
        Grid grid = new Grid(plateauCoordinates);
        Position playerPosition = Position.createPosition(grid, initialPosition);
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<>();
        player.find(playerPosition, Position.createPosition(grid, endPosition), visitedPositionList);
        assertArrayEquals(
                Arrays.asList(
                        Position.createPosition(grid, "0 0"),
                        Position.createPosition(grid, "0 1"),
                        Position.createPosition(grid, "0 2")//,
                ).toArray(new Position[3]),
                visitedPositionList.toArray(new Position[visitedPositionList.size()]));

    }

    @Test
    @Parameters({"0 0, 2 2, 3 3"})
    public void testPlayerFindsEndPositionWithSomeWall(
            String initialPosition,
            String endPosition,
            String gridPlateauCoordinates
    ) throws Exception {
        Grid grid = new Grid(gridPlateauCoordinates);
        grid.addWallAtCoordinates("1 0");
        grid.addWallAtCoordinates("2 0");
        grid.addWallAtCoordinates("0 2");
        grid.addWallAtCoordinates("1 2");
        Position playerPosition = Position.createPosition(grid, initialPosition);
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<>();
        assertTrue(player.find(playerPosition, Position.createPosition(grid, endPosition), visitedPositionList));
    }

    @Test
    public void testPlayerFindsEndPositionAndPathIsWhatExpectedWithSomeWall() {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        final String plateauCoordinates = "3 3";
        Grid grid = new Grid(plateauCoordinates);
        grid.addWallAtCoordinates("1 0");
        grid.addWallAtCoordinates("2 0");
        grid.addWallAtCoordinates("0 2");
        grid.addWallAtCoordinates("1 2");
        Position playerPosition = Position.createPosition(grid, initialPosition);
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<>();
        player.find(playerPosition, Position.createPosition(grid, endPosition), visitedPositionList);
        assertArrayEquals(
                Arrays.asList(
                        Position.createPosition(grid, "0 0"),
                        Position.createPosition(grid, "0 1"),
                        Position.createPosition(grid, "2 1")
                ).toArray(new Position[3]),
                visitedPositionList.toArray(new Position[visitedPositionList.size()]));
    }

}
