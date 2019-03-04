package com.katas.maze;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MazeAcceptanceTest {

    @Test
    @Parameters({
            "large_input.txt, " + AcceptanceOutputExpectation.largeInput,
            "medium_input.txt, " + AcceptanceOutputExpectation.mediumInput,
            "small_input.txt, " + AcceptanceOutputExpectation.smallInput,
            "small_wrap_input.txt, " + AcceptanceOutputExpectation.smallWrapInput,
            "sparse_large.txt, " + AcceptanceOutputExpectation.sparseLarge,
            "sparse_medium.txt, " + AcceptanceOutputExpectation.sparseMedium,
    })
    public void testFileInputTest(String resourceName, String expectedGridOutput) throws Exception {
        File file = new File(getClass().getClassLoader().getResource(resourceName).getFile());
        MazeFileReader mazeFileReader = new MazeFileReader(file);
        Grid grid = new Grid(mazeFileReader);
        Position playerPosition = Position.createPosition(grid, mazeFileReader.getInitialPosition());
        Position endPosition = Position.createPosition(grid, mazeFileReader.getEndPosition());
        SimpleSearchStrategy searchStrategy = new SimpleSearchStrategy();
        Player player = new Player(grid, playerPosition, searchStrategy);
        player.find(playerPosition, endPosition);
        assertEquals(expectedGridOutput.trim(), grid.toString().trim());
    }

}
