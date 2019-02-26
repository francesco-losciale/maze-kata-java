package com.katas.maze;

public class Grid {

    private Integer wallX;
    private Integer wallY;
    private Character[][] gridOutput; // 0,0 is upper-left cell, x goes right while y goes bottom

    public Grid(String plateauEdge) {
        initializeGrid(plateauEdge);
    }

    public Grid(String plateauCoordinates, String wallCellPosition) {
        this(plateauCoordinates);
        this.wallX = Integer.parseInt(wallCellPosition.substring(0, 1));
        this.wallY = Integer.parseInt(wallCellPosition.substring(0, 1));
    }

    public void markInitialCell(Position position) {
        setCellValue(position.getX(), position.getY(), 'S');
    }

    public void markCellUsed(Position position) {
        setCellValue(position.getX(), position.getY(), 'X');
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gridOutput.length; i++) {
            for (int j = 0; j < gridOutput[i].length; j++) {
                if (gridOutput[i][j] != null) {
                    sb.append(gridOutput[i][j]);
                    if (j + 1 < gridOutput[i].length && gridOutput[i][j + 1] != null) {
                        sb.append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void initializeGrid(String plateauEdge) {
        int limitX = Integer.parseInt(plateauEdge.substring(0, 1));
        int limitY = Integer.parseInt(plateauEdge.substring(2, 3));
        this.gridOutput = new Character[limitX][limitY];
        for (int i = 0; i < limitX; i++) {
            for (int j = 0; j < limitY; j++) {
                this.gridOutput[i][j] = '0';
            }
        }
    }

    private void setCellValue(int x, int y, Character value) {
        if (this.wallX != null && this.wallX == x &&
                this.wallY != null && this.wallY == y) {
            throw new RuntimeException("Here there is a wall");
        }
        this.gridOutput[y][x] = value;
    }
}
