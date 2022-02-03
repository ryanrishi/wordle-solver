package com.ryanrishi.wordle.solver;

import com.ryanrishi.wordle.Wordle;

public class BruteForceSolver extends DictionarySolver {
    @Override
    public String solve(Wordle wordle) {
        for (String word : dictionary) {
            wordle.guess(word);

            if (wordle.isSolved()) {
                return word;
            }
        }

        return null;
    }
}
