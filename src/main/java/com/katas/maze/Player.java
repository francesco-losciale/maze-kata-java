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

    
    public Position find(Position startPosition, Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return endPosition; // TODO or exception?
        }
        checkAllRequisitesDefined();
        for (Character instruction : this.strategy.getPossibleDirection(startPosition)) {
            try {
                Position newPosition = calculateNextPosition(startPosition, instruction); // TODO fix this...
                if (!newPosition.equals(endPosition)) {
                    this.strategy.getVisitedPositionList().add(newPosition);
                    this.grid.markCellUsed(newPosition);// TODO added for testing acceptance
                    return find(newPosition, endPosition);
                } else {
                    for (Position position : this.strategy.getVisitedPositionList()) {
                        if (this.position != position) {
                            //TODO improve, done to avoid to overwrite S
                            this.grid.markCellUsed(position);
                        }
                    }
                    this.strategy.getVisitedPositionList().add(endPosition);
                    return endPosition;
                }
            } catch (WallFoundException e) {
                //TODO logging
            }
        }
        return startPosition;
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
        if (this.grid.isPositionAgainstWall(newPosition)) {
            throw new WallFoundException();
        }
        return newPosition;
    }

}
