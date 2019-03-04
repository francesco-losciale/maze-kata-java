package com.katas.maze;

import java.util.ArrayList;
import java.util.List;

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

    public void movePlayer(Character direction) {
        Position newPosition = Position.calculateNextPosition(this.position, direction);
        if (!this.grid.isDirectionAgainsWall(newPosition)) {
            this.position = newPosition;
            this.grid.markCellUsed(newPosition);
        }
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean find(Position startPosition, Position endPosition) {
        return find(startPosition, endPosition, new ArrayList<>());
    }


    public boolean find(Position startPosition, Position endPosition, List<Position> visitedPositionList) {
        if (visitedPositionList.contains(startPosition) ||
                this.grid.isDirectionAgainsWall(startPosition)) {
            return false;
        }
        if (startPosition.equals(endPosition)) {
            return true;
        }
        visitedPositionList.add(startPosition);
        for (Character direction : this.strategy.getPossibleDirection(startPosition)) {
            Position newPosition = Position.calculateNextPosition(startPosition, direction);
            boolean found = find(newPosition, endPosition, visitedPositionList);
            if (found) {
                this.grid.drawPathIntoGrid(this.position, endPosition, visitedPositionList);
                return true;
            }
        }
        visitedPositionList.remove(startPosition);
        return false;
    }

}
