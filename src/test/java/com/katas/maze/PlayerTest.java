package com.katas.maze;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
@RunWith(JUnitParamsRunner.class)
public class PlayerTest {

    @Test
    public void testPlayerInstantiation() {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 2");
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, strategy);
        assertEquals(player.getPosition().toString(), "2 2");
    }

    // TODO you can move Position.createPosition a level up
    // TODO this way you don't need to pass grid both ways


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
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Whitebox.setInternalState(strategy, "visitedPositionList", visitedPositionList);
        Player player = new Player(grid, playerPosition, strategy);
        assertTrue(player.find(playerPosition, Position.createPosition(grid, endPosition)));
    }

    @Test
    public void testPlayerFindsEndPositionAndPathIsWhatExpectedWithoutAnyWall() {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        final String plateauCoordinates = "3 3";
        Grid grid = new Grid(plateauCoordinates);
        Position playerPosition = Position.createPosition(grid, initialPosition);
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        Whitebox.setInternalState(strategy, "visitedPositionList", visitedPositionList);
        player.find(playerPosition, Position.createPosition(grid, endPosition));
        assertArrayEquals(
                Arrays.asList(
                        Position.createPosition(grid, "0 0"),
                        Position.createPosition(grid, "1 0"),
                        Position.createPosition(grid, "2 0"),
                        Position.createPosition(grid, "2 2")
                ).toArray(new Position[4]),
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
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Whitebox.setInternalState(strategy, "visitedPositionList", visitedPositionList);
        Player player = new Player(grid, playerPosition, strategy);
        assertTrue(player.find(playerPosition, Position.createPosition(grid, endPosition)));
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
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        Player player = new Player(grid, playerPosition, strategy);
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        Whitebox.setInternalState(strategy, "visitedPositionList", visitedPositionList);
        player.find(playerPosition, Position.createPosition(grid, endPosition));
        assertArrayEquals(
                Arrays.asList(
                        Position.createPosition(grid, "0 0"),
                        Position.createPosition(grid, "0 1"),
                        Position.createPosition(grid, "1 1"),
                        Position.createPosition(grid, "2 1"),
                        Position.createPosition(grid, "2 2")
                ).toArray(new Position[5]),
                visitedPositionList.toArray(new Position[visitedPositionList.size()]));

    }


}
