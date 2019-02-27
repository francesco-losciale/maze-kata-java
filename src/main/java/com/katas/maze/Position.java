package com.katas.maze;

public class Position {

    private final int plateauX;
    private final int plateauY;
    private int currentY;
    private int currentX;

    //TODO make private constructor and use factory
    public Position(String currentPosition, String plateauCoordinates) {
        this.currentX = Integer.parseInt(currentPosition.substring(0, 1));
        this.currentY = Integer.parseInt(currentPosition.substring(2, 3));
        this.plateauX = Integer.parseInt(plateauCoordinates.substring(0, 1));
        this.plateauY = Integer.parseInt(plateauCoordinates.substring(2, 3));
    }

    //TODO can you extract all this logic about the plateau outside in the Player
    public Position moveEast() {
        int currentX = this.currentX + 1;
        if (currentX  >= this.plateauX) {
            currentX = 0;
        }
        return new Position(currentX + " " + this.currentY, this.plateauX + " " + this.plateauY);
    }

    public Position moveWest() {
        this.currentX -= 1;
        if (this.currentX == -1) {
            this.currentX = this.plateauX - 1;
        }
        return new Position(this.toString(), this.plateauX + " " + this.plateauY);
    }

    public Position moveNorth() {
        this.currentY -= 1;
        if (this.currentY == -1) {
            this.currentY = this.plateauY - 1;
        }
        return new Position(this.toString(), this.plateauX + " " + this.plateauY);
    }

    public Position moveSouth() {
        this.currentY += 1;
        if (this.currentY >= this.plateauY) {
            this.currentY = 0;
        }
        return new Position(this.toString(), this.plateauX + " " + this.plateauY);
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
