package com.katas.maze;

public class Grid {

    private String[][] gridOutput;

    public Grid(String plateauEdge, String startPosition) {
        initializeGrid(plateauEdge);
        setCellValue(startPosition, "S");
    }

    public void markCell(String position) {
        setCellValue(position, "X");
    }

    @Override
    public String toString() {
        String output = "";
        for (String[] line : gridOutput) {
            for (int index = 0; index < line.length; index++) {
                if (line[index] != null) {
                    output = output + line[index];
                    if (index + 1 < line.length && line[index + 1] != null) {
                        output += " ";
                    }
                }
            }
            output += "\n";
        }
        return output;
    }

    private void initializeGrid(String plateauEdge) {
        int limitX = Integer.parseInt(plateauEdge.substring(0, 1));
        int limitY = Integer.parseInt(plateauEdge.substring(2, 3));
        this.gridOutput = new String[limitX][limitY];
        for (int i = 0; i < limitX; i++) {
            for (int j = 0; j < limitY; j++) {
                this.gridOutput[i][j] = "0";
            }
        }
    }

    private void setCellValue(String position, String value) {
        int positionX = Integer.parseInt(position.substring(0, 1));
        int positionY = Integer.parseInt(position.substring(2, 3));
        this.gridOutput[positionY][positionX] = value;
    }
}
