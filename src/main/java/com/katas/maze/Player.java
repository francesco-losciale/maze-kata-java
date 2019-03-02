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
        Position newPosition = executePositionMovement(this.position, instruction);
        this.position = newPosition;
        this.grid.markCellUsed(newPosition);
    }

    public Position getPosition() {
        return this.position;
    }

    public Position find(Position startPosition, Position endPosition, List<Position> visitedPositionList) {
        if (startPosition.equals(endPosition)) {
            return endPosition; // TODO or exception?
        }
        for (String instruction : getPossibleOtherCells(startPosition, visitedPositionList)) {
            Position newPosition = executePositionMovement(startPosition, instruction.charAt(0)); // TODO fix this...
            if (!newPosition.equals(endPosition)) {
                visitedPositionList.add(newPosition);
                return find(newPosition, endPosition, visitedPositionList);
            } else {
                visitedPositionList.add(endPosition);
                return endPosition;
            }
        }
        return startPosition;
    }

    public List<String> getPossibleOtherCells(Position startPosition, List<Position> visitedPositionList) {
        List<String> result = new ArrayList<String>();
        try {
            if (!visitedPositionList.contains(startPosition.moveEast())) {
                result.add("E");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(startPosition.moveWest())) {
                result.add("W");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(startPosition.moveNorth())) {
                result.add("N");
            }
        } catch (RuntimeException e) {
        }
        try {
            if (!visitedPositionList.contains(startPosition.moveSouth())) {
                result.add("S");
            }
        } catch (RuntimeException e) {
        }
        return result;
    }

    private Position executePositionMovement(Position startPosition, Character instruction) {
        Position newPosition = startPosition;
        if (instruction == 'E') {
            newPosition = startPosition.moveEast();
        }
        if (instruction == 'W') {
            newPosition = startPosition.moveWest();
        }
        if (instruction == 'N') {
            newPosition = startPosition.moveNorth();
        }
        if (instruction == 'S') {
            newPosition = startPosition.moveSouth();
        }
        if (this.grid.isPositionAgainstWall(newPosition)) {
            throw new RuntimeException("Wall Found!");
        }
        return newPosition;
    }

}
