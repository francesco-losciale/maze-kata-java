package com.katas.maze;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {

    private Grid grid;
    private Position position;

    public Player(Grid grid, Position position) {
        this.grid = grid;
        this.position = position;
        this.grid.markInitialCell(position);
    }

    public void executeInstruction(Character instruction) {
        Position newPosition = this.position;
        if (instruction == 'E') {
            newPosition = this.position.moveEast();
        }
        if (instruction == 'W') {
            newPosition = this.position.moveWest();
        }
        if (instruction == 'N') {
            newPosition = this.position.moveNorth();
        }
        if (instruction == 'S') {
            newPosition = this.position.moveSouth();
        }
        if (this.grid.isPositionAgainstWall(newPosition)) {
            throw new RuntimeException("Wall Found!");
        }
        this.position = newPosition;
        this.grid.markCellUsed(newPosition);
    }

    public Position getPosition() {
        return this.position;
    }

    public List<String> findEndPosition() {
        return Collections.emptyList();
    }
}
