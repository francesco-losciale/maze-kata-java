package com.katas.maze;

public class Player {
    private String currentPosition;
    private Grid grid;

    public Player(String currentPosition, Grid grid) {
        this.currentPosition = currentPosition;
        this.grid = grid;
    }

    public String executeMove(Character instruction) {
        if (instruction == 'E') {
            this.currentPosition = "1 0";
            this.grid.markCell(currentPosition);
        }
        return currentPosition;
    }
}
