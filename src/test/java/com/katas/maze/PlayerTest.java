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
        Player player = new Player(grid, playerPosition);
        assertEquals(player.getPosition().toString(), "2 2");
    }

    // TODO you can move Position.createPosition a level up
    // TODO this way you don't need to pass grid both ways



    // TODO parametrize this two methods below

    @Test
    @Parameters({
            "2 2, E N S W"
    })
    public void testPlayerWhereCanMove(String currentPosition, String expectedPossibleMovements) throws Exception {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, currentPosition);
        Player player = new Player(grid, playerPosition);
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        List<String> possibleOtherCellsList = player.getPossibleOtherCells(playerPosition, visitedPositionList);
        Collections.sort(possibleOtherCellsList);
        assertEquals(expectedPossibleMovements, String.join(" ", possibleOtherCellsList));
    }

    @Test
    public void testPlayerWhereCanMoveAvoidingVisitedPositions() throws Exception {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 0");
        Player player = new Player(grid, playerPosition);
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        // cannot go east
        visitedPositionList.add(Position.createPosition(grid, "0 0"));
        assertFalse(player.getPossibleOtherCells(playerPosition, visitedPositionList).contains("E"));
        assertTrue(player.getPossibleOtherCells(playerPosition, visitedPositionList).contains("N"));
        assertTrue(player.getPossibleOtherCells(playerPosition, visitedPositionList).contains("W"));
        assertTrue(player.getPossibleOtherCells(playerPosition, visitedPositionList).contains("S"));
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
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        Player player = new Player(grid, playerPosition);
        assertEquals(Position.createPosition(grid, endPosition),
                player.find(playerPosition, Position.createPosition(grid, endPosition),
                        visitedPositionList));
    }

    @Test
    public void testPlayerFindsEndPositionAndPathIsWhatExpected() {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        final String plateauCoordinates = "3 3";
        Grid grid = new Grid(plateauCoordinates);
        Position playerPosition = Position.createPosition(grid, initialPosition);
        Player player = new Player(grid, playerPosition);
        List<Position> visitedPositionList = new ArrayList<Position>();
        visitedPositionList.add(playerPosition);
        player.find(playerPosition, Position.createPosition(grid, endPosition), visitedPositionList);
        assertArrayEquals(
                Arrays.asList(
                        Position.createPosition(grid, "0 0"),
                        Position.createPosition(grid, "1 0"),
                        Position.createPosition(grid, "2 0"),
                        Position.createPosition(grid, "2 2")
                        ).toArray(new Position[3]),
                visitedPositionList.toArray(new Position[visitedPositionList.size()]));

    }

}
