package com.katas.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MazeFileReader {

    private final String plateauCoordinates;
    private final String initialPosition;
    private final String endPosition;
    private final Character[][] grid;

    public MazeFileReader(String resourceName) throws Exception {
        File file = new File(getClass().getClassLoader().getResource(resourceName).getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            this.plateauCoordinates = br.readLine();
            int plateauX = Integer.parseInt(this.plateauCoordinates.split(" ")[0]);
            int plateauY = Integer.parseInt(this.plateauCoordinates.split(" ")[1]);
            this.grid = new Character[plateauX][plateauY];
            for (int y = 0; y < plateauY; y++) {
                String[] line = br.readLine().split(" ");
                for (int x = 0; x < plateauX; x++) {
                    this.grid[y][x] = line[x].charAt(0);
                }
            }
            this.initialPosition = br.readLine();
            this.endPosition = br.readLine();
        }
    }

    public String getPlateauCoordinates() {
        return plateauCoordinates;
    }

    public String getInitialPosition() {
        return initialPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public Character[][] getGrid() {
        return grid;
    }
}
