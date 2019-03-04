package com.katas.maze;

import java.util.List;

public interface SearchStrategy {

    List<Character> getPossibleDirection(Position startPosition);
}
