package com.ryanrishi;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IterativeSolverWithWordFrequencyTest {
    @Test
    void test226_iterativeFrequency_badSeed() {
        String answer = "light";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency(Collections.singletonList("other"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("226 (iterative + frequency, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test226_iterativeFrequency() {
        String answer = "light";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("226 (iterative + frequency): " + wordle.getNumGuesses());
    }

    @Test
    void testTight_iterativeFrequency() {
        String answer = "tight";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("tight (iterative + frequency): " + wordle.getNumGuesses());
    }

    @Test
    void testTight_iterativeFrequency_badSeed() {
        String answer = "tight";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency(Collections.singletonList("light"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("tight (iterative + frequency, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220127_iterativeFrequency_goodSeed() {
        String answer = "mount";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-27 (iterative + frequency, good seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220127_iterativeFrequency_badSeed() {
        String answer = "mount";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency(Collections.singletonList("adieu"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-27 (iterative + frequency, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220128_iterativeFrequency_badSeed() {
        String answer = "perky";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolverWithWordFrequency(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-28 (iterative + frequency, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220128_iterativeFrequency_goodSeed() {
        String answer = "perky";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("pesky"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-28 (iterative + frequency, good seed): " + wordle.getNumGuesses());
    }
}
