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
        Position newPosition = executePositionMovement(instruction);
        this.position = newPosition;
        this.grid.markCellUsed(newPosition);
    }

    public Position getPosition() {
        return this.position;
    }

    public Position find(Position endPosition, List<Position> visitedPositionList) {
        for (String instruction : getPossibleOtherCells(visitedPositionList)) {
            executePositionMovement(instruction.charAt(0)); // TODO fix this...
            if (this.position != endPosition) {
                visitedPositionList.add(this.position);
                return find(endPosition, visitedPositionList);
            } else {
                return endPosition;
            }
        }
        return this.position;
    }

    public List<String> getPossibleOtherCells(List<Position> visitedPositionList) {
        List<String> result = new ArrayList<String>();
        try {
            if (!visitedPositionList.contains(this.position.moveEast())) {
                result.add("E");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveWest())) {
                result.add("W");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveNorth())) {
                result.add("N");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(this.position.moveSouth())) {
                result.add("S");
            }
        } catch (RuntimeException e) {
        }
        return result;
    }

    private Position executePositionMovement(Character instruction) {
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
        return newPosition;
    }

}
