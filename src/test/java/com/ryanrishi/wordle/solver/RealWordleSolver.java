package com.ryanrishi.wordle.solver;

import com.ryanrishi.wordle.Wordle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Uses list of real Wordle solutions to test each solver
 */
public class RealWordleSolver {
    Set<String> words = new LinkedHashSet<>();

    @ParameterizedTest
    @CsvFileSource(resources = "/answers.csv", numLinesToSkip = 1)
    void testBruteForceSolver(int gameNumber, String answer) {
        answer = answer.toLowerCase();
        Wordle wordle = new Wordle(answer);
        Solver solver = new BruteForceSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.printf("'%s' (#%d) brute force: %d%n", answer, gameNumber, wordle.getNumGuesses());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/answers.csv", numLinesToSkip = 1)
    void testIterativeSolver(int gameNumber, String answer) {
        answer = answer.toLowerCase();
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.printf("'%s' (#%d) iterative: %d%n", answer, gameNumber, wordle.getNumGuesses());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/answers.csv", numLinesToSkip = 1)
    void testIterativeFrequencySolver(int gameNumber, String answer) {
        answer = answer.toLowerCase();
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency();
        assertEquals(answer, solver.solve(wordle));
        System.out.printf("'%s' (#%d) iterative + frequency: %d%n", answer, gameNumber, wordle.getNumGuesses());
    }
}
