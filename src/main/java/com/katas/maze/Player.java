package com.katas.maze;

public class Player {

    private Grid grid;
    private Position position;

    public Player(Grid grid, Position position) {
        this.grid = grid;
        this.position = position;
        this.grid.markInitialCell(position);
    }

    public void executeInstruction(Character instruction) {
        if (instruction == 'E') {
            position.moveEast();
        }
        if (instruction == 'W') {
            position.moveWest();
        }
        if (instruction == 'N') {
            position.moveNorth();
        }
        if (instruction == 'S') {
            position.moveSouth();
        }
        this.grid.markCellUsed(position);
    }

    public Position getPosition() {
        return this.position;
    }
}
