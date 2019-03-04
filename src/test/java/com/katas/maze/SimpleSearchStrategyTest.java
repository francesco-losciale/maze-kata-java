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
public class SimpleSearchStrategyTest {

    @Test
    @Parameters({
            "2 2, E N S W"
    })
    public void testPlayerWhereCanMove(String currentPosition, String expectedPossibleMovements) {
        Grid grid = new Grid("3 3");
        Position playerPosition = Position.createPosition(grid, currentPosition);
        SimpleSearchStrategy strategy = new SimpleSearchStrategy();
        List<Character> possibleDirectionList = strategy.getPossibleDirection(playerPosition);
        for (Character character : possibleDirectionList) {
            assertTrue(expectedPossibleMovements.contains(character.toString()));
        }
    }

}
