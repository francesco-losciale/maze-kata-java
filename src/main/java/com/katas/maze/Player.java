package com.katas.maze;

import java.util.ArrayList;
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

    public Position findEndPosition() {
        Position endPosition = Position.createPosition(this.grid, "0 0");
        return endPosition;
    }

    public List<Character> getPossibleOtherCells(List<Position> visitedPositionList) {
        List<Character> result = new ArrayList<Character>();
        try {
            if (!visitedPositionList.contains(this.position.moveEast())) {
                result.add('E');
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveWest())) {
                result.add('W');
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveNorth())) {
                result.add('N');
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveSouth())) {
                result.add('S');
            }
        } catch (RuntimeException e) {
        }
        return result;
    }

}
