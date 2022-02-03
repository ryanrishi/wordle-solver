package com.ryanrishi;

public interface Solver {
    /**
     * @param wordle the game
     * @return the answer to the game, or null if the game could not be solved
     */
    String solve(Wordle wordle);
}
