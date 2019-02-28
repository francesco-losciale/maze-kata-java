package com.katas.maze;

public class Position {

    private final int plateauX;
    private final int plateauY;
    private int currentY;
    private int currentX;

    private Position(String currentPosition, int plateauX, int plateauY) {
        this.currentX = Integer.parseInt(currentPosition.substring(0, 1));
        this.currentY = Integer.parseInt(currentPosition.substring(2, 3));
        this.plateauX = plateauX;
        this.plateauY = plateauY;
    }

    static public Position createInitialPosition(Grid grid, String currentPosition) {
        return new Position(currentPosition, grid.getLimitX(), grid.getLimitY());
    }

    public Position moveEast() {
        int currentX = this.currentX + 1;
        if (currentX  >= this.plateauX) {
            currentX = 0;
        }
        return new Position(currentX + " " + this.currentY, this.plateauX, this.plateauY);
    }

    public Position moveWest() {
        this.currentX -= 1;
        if (this.currentX == -1) {
            this.currentX = this.plateauX - 1;
        }
        return new Position(this.toString(), this.plateauX, this.plateauY);
    }

    public Position moveNorth() {
        this.currentY -= 1;
        if (this.currentY == -1) {
            this.currentY = this.plateauY - 1;
        }
        return new Position(this.toString(), this.plateauX, this.plateauY);
    }

    public Position moveSouth() {
        this.currentY += 1;
        if (this.currentY >= this.plateauY) {
            this.currentY = 0;
        }
        return new Position(this.toString(), this.plateauX, this.plateauY);
    }

    public int getY() {
        return this.currentY;
    }

    public int getX() {
        return this.currentX;
    }

    @Override
    public String toString() {
        return currentX + " " + currentY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (currentY != position.currentY) return false;
        return currentX == position.currentX;
    }

    @Override
    public int hashCode() {
        int result = currentY;
        result = 31 * result + currentX;
        return result;
    }
}
