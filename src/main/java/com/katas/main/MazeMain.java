package com.katas.main;

import com.katas.maze.*;

import java.io.File;
import java.io.IOException;

public class MazeMain {

    static public void main(String[] args) {
        String inputMazeFilePath = args[0];
        System.out.println(inputMazeFilePath);
        try {
            File file = new File(inputMazeFilePath);
            MazeFileReader mazeFileReader = new MazeFileReader(file);
            Grid grid = new Grid(mazeFileReader);
            Position playerPosition = Position.createPosition(grid, mazeFileReader.getInitialPosition());
            Position endPosition = Position.createPosition(grid, mazeFileReader.getEndPosition());
            SimpleSearchStrategy searchStrategy = new SimpleSearchStrategy();
            Player player = new Player(grid, playerPosition, searchStrategy);
            player.find(playerPosition, endPosition);
            System.out.println(grid);
        } catch (IOException e) {
            System.out.println("File not found: " + inputMazeFilePath);
        }
    }
}
