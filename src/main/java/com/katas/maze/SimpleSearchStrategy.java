package com.katas.maze;

import java.util.Arrays;
import java.util.List;

public class SimpleSearchStrategy implements SearchStrategy {

    public List<Character> getPossibleDirection(Position startPosition) {
        return Arrays.asList('S', 'W', 'E', 'N');
    }

}
