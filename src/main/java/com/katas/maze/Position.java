package com.katas.maze;

public class Position {

    private final int plateauX;
    private final int plateauY;
    private int currentY;
    private int currentX;

    protected Position(String currentPosition, int plateauX, int plateauY) {
        this.currentX = Integer.parseInt(currentPosition.split(" ")[0]);
        this.currentY = Integer.parseInt(currentPosition.split(" ")[1]);
        this.plateauX = plateauX;
        this.plateauY = plateauY;
    }

    static public Position createPosition(Grid grid, String currentPosition) {
//        return new Position(currentPosition, grid.getLimitX(), grid.getLimitY());
        return new Position(currentPosition, grid.getLimitY(), grid.getLimitX());

    }

    public Position moveEast() {
        int currentX = this.currentX + 1;
        if (currentX  >= this.plateauX) {
            currentX = 0;
        }
        return new Position(currentX + " " + this.currentY, this.plateauX, this.plateauY);
    }

    public Position moveWest() {
        int currentX = this.currentX - 1;
        if (currentX == -1) {
            currentX = this.plateauX - 1;
        }
        return new Position(currentX + " " + this.currentY, this.plateauX, this.plateauY);
    }

    public Position moveNorth() {
        int currentY = this.currentY - 1;
        if (currentY == -1) {
            currentY = this.plateauY - 1;
        }
        return new Position(this.currentX + " " + currentY, this.plateauX, this.plateauY);
    }

    public Position moveSouth() {
        int currentY = this.currentY + 1;
        if (currentY >= this.plateauY) {
            currentY = 0;
        }
        return new Position(this.currentX + " " + currentY, this.plateauX, this.plateauY);
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
