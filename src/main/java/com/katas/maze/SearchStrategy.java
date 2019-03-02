package com.katas.maze;

import java.util.List;

public interface SearchStrategy {

    public List<Character> getPossibleDirection(Position startPosition);
    public List<Position> getVisitedPositionList();
}
