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
        final Position currentPosition = getPosition();
        if (instruction == 'E') {
            this.position = this.position.moveEast();
        }
        if (instruction == 'W') {
            this.position = this.position.moveWest();
        }
        if (instruction == 'N') {
            this.position = this.position.moveNorth();
        }
        if (instruction == 'S') {
            this.position = this.position.moveSouth();
        }
        if (this.grid.isPositionAgainstWall(position)) {
            this.position = currentPosition;
            throw new RuntimeException("Wall Found!");
        }
        this.grid.markCellUsed(position);
    }

    public Position getPosition() {
        return this.position;
    }
}
