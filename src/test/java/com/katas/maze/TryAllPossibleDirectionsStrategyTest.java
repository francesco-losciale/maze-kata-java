package com.katas.maze;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitParamsRunner.class)
public class TryAllPossibleDirectionsStrategyTest {


    // TODO parametrize this two methods below

    @Test
    @Parameters({
            "2 2, E N S W"
    })
    public void testPlayerWhereCanMove(String currentPosition, String expectedPossibleMovements) throws Exception {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, currentPosition);
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);
        List<Character> possibleDirectionList = strategy.getPossibleDirection(playerPosition);
        for (Character character : possibleDirectionList) {
            assertTrue(expectedPossibleMovements.contains(character.toString()));
        }
    }

    @Test
    public void testPlayerWhereCanMoveAvoidingVisitedPositions() {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, "2 0");
        TryAllPossibleDirectionsStrategy strategy = new TryAllPossibleDirectionsStrategy(playerPosition);

        //mocking visited positions
        List<Position> visitedPositionList = new ArrayList<>();
        visitedPositionList.add(playerPosition);
        visitedPositionList.add(Position.createPosition(grid, "0 0"));
        Whitebox.setInternalState(strategy, "visitedPositionList", visitedPositionList);

        List<Character> possibleDirectionList = strategy.getPossibleDirection(playerPosition);
        String expectedPossibleMovements = "N W S";
        for (Character character : possibleDirectionList) {
            assertTrue(expectedPossibleMovements.contains(character.toString()));
            assertFalse("E".contains(character.toString()));
        }
    }
}
