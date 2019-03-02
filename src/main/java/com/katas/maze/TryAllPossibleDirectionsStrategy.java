package com.katas.maze;

import java.util.ArrayList;
import java.util.List;

public class TryAllPossibleDirectionsStrategy implements SearchStrategy {

    private List<Position> visitedPositionList;

    public TryAllPossibleDirectionsStrategy(Position startPosition) {
        this.visitedPositionList = new ArrayList<Position>();
//        this.visitedPositionList.add(startPosition);
    }

    public List<Position> getVisitedPositionList() {
        return visitedPositionList;
    }

    public List<Character> getPossibleDirection(Position startPosition) {
        List<Character> possibleDirectionList = new ArrayList<Character>();
        tryMovingSouth(startPosition, visitedPositionList, possibleDirectionList);
        tryMovingWest(startPosition, visitedPositionList, possibleDirectionList);
        tryMovingEast(startPosition, visitedPositionList, possibleDirectionList);
        tryMovingNorth(startPosition, visitedPositionList, possibleDirectionList);
        return possibleDirectionList;
    }


    private void tryMovingSouth(Position startPosition, List<Position> visitedPositionList, List<Character> possibleDirectionList) {
        try {
            if (!visitedPositionList.contains(startPosition.moveSouth())) {
                possibleDirectionList.add('S');
            }
        } catch (RuntimeException e) {
        }
    }

    private void tryMovingNorth(Position startPosition, List<Position> visitedPositionList, List<Character> possibleDirectionList) {
        try {
            if (!visitedPositionList.contains(startPosition.moveNorth())) {
                possibleDirectionList.add('N');
            }
        } catch (RuntimeException e) {
        }
    }

    private void tryMovingWest(Position startPosition, List<Position> visitedPositionList, List<Character> possibleDirectionList) {
        try {
            if (!visitedPositionList.contains(startPosition.moveWest())) {
                possibleDirectionList.add('W');
            }
        } catch (RuntimeException e) {
        }
    }

    private void tryMovingEast(Position startPosition, List<Position> visitedPositionList, List<Character> possibleDirectionList) {
        try {
            if (!visitedPositionList.contains(startPosition.moveEast())) {
                possibleDirectionList.add('E');
            }
        } catch (RuntimeException e) {
        }
    }


}
