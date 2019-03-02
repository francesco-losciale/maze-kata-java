package com.katas.maze;

public class Player {

    private Grid grid;
    private Position position;
    private SearchStrategy strategy;

    public Player(Grid grid, Position position, SearchStrategy strategy) {
        this.grid = grid;
        this.position = position;
        this.strategy = strategy;
        this.grid.markInitialCell(position);
    }

    public Player(Grid grid, Position position) {
        this.grid = grid;
        this.position = position;
        this.grid.markInitialCell(position);
    }

    public void executeInstruction(Character instruction) {
        Position newPosition = calculateNextPosition(this.position, instruction);
        this.position = newPosition;
        this.grid.markCellUsed(newPosition);
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean find(Position startPosition, Position endPosition) {
        if (this.strategy.getVisitedPositionList().contains(startPosition) ||
                this.grid.isPositionAgainstWall(startPosition)) {
            return false;
        }
        if (startPosition.equals(endPosition)) {
            return true;
        }
        this.strategy.getVisitedPositionList().add(startPosition);
        for (Character direction : this.strategy.getPossibleDirection(startPosition)) {
            Position newPosition = calculateNextPosition(startPosition, direction); // TODO fix this...
            boolean found = find(newPosition, endPosition);
            if (found) {
                return true;
            }
        }
        this.strategy.getVisitedPositionList().remove(startPosition);
        return false;
    }

    private void checkAllRequisitesDefined() {
        if (this.strategy == null) {
            new UnsupportedOperationException("Implementation error: no strategy defined for finding next cells");
        }
    }

    private Position calculateNextPosition(Position startPosition, Character instruction) {
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
//        if (this.grid.isPositionAgainstWall(newPosition)) {
//            throw new WallFoundException();
//        }
        return newPosition;
    }

}
