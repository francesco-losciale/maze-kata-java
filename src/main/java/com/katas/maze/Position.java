package com.katas.maze;

public class Position {

    private int currentY;
    private int currentX;

    public Position(String currentPosition) {
        this.currentX = Integer.parseInt(currentPosition.substring(0, 1));
        this.currentY = Integer.parseInt(currentPosition.substring(2, 3));

    }

    public void moveEast() {
        this.currentX += 1;
    }

    public void moveWest() {
        this.currentX -= 1;
    }

    public void moveNorth() {
        this.currentY -= 1;
    }

    public void moveSouth() {
        this.currentY += 1;
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
