package com.katas.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junitparams.Parameters;

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
        player.getPossibleOtherCells(visitedPositionList).
        assertEquals(expectedPossibleMovements, String.join(" ", );
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('E'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('N'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('W'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('S'));
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
        assertFalse(player.getPossibleOtherCells(visitedPositionList).contains('E'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('N'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('W'));
        assertTrue(player.getPossibleOtherCells(visitedPositionList).contains('S'));
    }

    @Test
    public void testPlayerFindsEndPositionWithoutAnyWall() throws Exception {
        final String initialPosition = "0 0";
        final String endPosition = "2 2";
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, initialPosition);
        Player player = new Player(grid, playerPosition);
        assertEquals(Position.createPosition(grid, endPosition), player.findEndPosition());
    }

}
