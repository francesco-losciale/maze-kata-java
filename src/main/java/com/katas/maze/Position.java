package com.katas.maze;

public class Position {

    private final int plateauX;
    private final int plateauY;
    private int currentY;
    private int currentX;

    public Position(String currentPosition, String plateauCoordinates) {
        this.currentX = Integer.parseInt(currentPosition.substring(0, 1));
        this.currentY = Integer.parseInt(currentPosition.substring(2, 3));
        this.plateauX = Integer.parseInt(plateauCoordinates.substring(0, 1));
        this.plateauY = Integer.parseInt(plateauCoordinates.substring(2, 3));
    }

    public void moveEast() {
        this.currentX += 1;
        if (this.currentX >= this.plateauX) {
            this.currentX = 0;
        }
    }

    public void moveWest() {
        this.currentX -= 1;
        if (this.currentX == -1) {
            this.currentX = this.plateauX - 1;
        }
    }

    public void moveNorth() {
        this.currentY -= 1;
        if (this.currentY == -1) {
            this.currentY = this.plateauY - 1;
        }
    }

    public void moveSouth() {
        this.currentY += 1;
        if (this.currentY >= this.plateauY) {
            this.currentY = 0;
        }
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
}
