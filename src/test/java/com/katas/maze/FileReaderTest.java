package com.katas.maze;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {

    @Test
    public void testOpenFile() {
        File file = new File(getClass().getClassLoader().getResource("input.txt").getFile());
        assertTrue(file.isFile());
    }

    @Test
    public void testReadPlateauCoordinates() throws Exception {
        String resourceName = "input.txt";
        File file = new File(getClass().getClassLoader().getResource(resourceName).getFile());
        MazeFileReader mazeFileReader = new MazeFileReader(file);
        assertEquals("5 5", mazeFileReader.getPlateauCoordinates());
        assertEquals("1 1", mazeFileReader.getInitialPosition());
        assertEquals("3 3", mazeFileReader.getEndPosition());
        char[][] grid = new char[][] {
                {'1', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'},
        };
        assertArrayEquals(grid, mazeFileReader.getGrid());
    }


}
