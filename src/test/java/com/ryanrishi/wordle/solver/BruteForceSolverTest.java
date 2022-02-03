package com.ryanrishi.wordle.solver;

import com.ryanrishi.wordle.Wordle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BruteForceSolverTest {
    @Test
    void testBruteForce_about() {
        String answer = "about";
        Wordle wordle = new Wordle(answer);
        wordle.setDebug(false);
        Solver solver = new BruteForceSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("brute force: " + wordle.getNumGuesses());
    }

    @Test
    void testBruteForce_zorro() {
        String answer = "zorro";
        Wordle wordle = new Wordle(answer);
        wordle.setDebug(false);
        Solver solver = new BruteForceSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("brute force: " + wordle.getNumGuesses());
    }
}
