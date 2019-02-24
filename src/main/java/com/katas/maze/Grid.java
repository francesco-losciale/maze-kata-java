package com.katas.maze;

import java.util.Arrays;

public class Grid {

    private Character[][] gridOutput;

    public Grid(String plateauEdge) {
        initializeGrid(plateauEdge);
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
//        sb.append(Arrays.deepToString(gridOutput));
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
        /*
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
        return output;*/
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
        this.gridOutput[x][y] = value;
    }
}
