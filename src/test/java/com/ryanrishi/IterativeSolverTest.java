package com.ryanrishi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IterativeSolverTest {
    @Test
    void testIterative_seedContainsSomeLettersInAnswer() {
        String answer = "robot";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("iterative (good seed): " + wordle.getNumGuesses());   // TODO this doesn't improve results, both good seed and bad seed take 3 guesses
    }

    @Test
    void testIterative_seedDoesNotContainAnyLettersInAnswer() {
        String answer = "robot";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("adieu"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("iterative (bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void testIterative_multipleOccurrencesOfSameLetter() {
        String answer = "feeds";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("guess"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("iterative (multiple occurrences of same letter): " + wordle.getNumGuesses());
    }

    @Test
    void test20220127_iterative_goodSeed() {
        String answer = "mount";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-27 (iterative, good seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220127_iterative_badSeed() {
        String answer = "mount";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("adieu"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-27 (iterative, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220128_iterative_badSeed() {
        String answer = "perky";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-28 (iterative, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220128_iterative_goodSeed() {
        String answer = "perky";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("pesky"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-28 (iterative, good seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220129_iterative_badSeed() {
        String answer = "could";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Arrays.asList("ables", "peril"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-29 (iterative, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test20220129_iterative_goodSeed() {
        String answer = "could";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("2022-01-29 (iterative, good seed): " + wordle.getNumGuesses());
    }

    @Test
    void test226_iterative_goodSeed() {
        String answer = "light";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("cough"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("226 (iterative, good seed): " + wordle.getNumGuesses());
    }

    @Test
    void test226_iterative_badSeed() {
        String answer = "light";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("about"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("226 (iterative, bad seed): " + wordle.getNumGuesses());
    }

    @Test
    void test226_iterative() {
        String answer = "light";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("226 (iterative): " + wordle.getNumGuesses());
    }

    @Test
    void testTight_iterative() {
        String answer = "tight";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver();
        assertEquals(answer, solver.solve(wordle));
        System.out.println("tight (iterative): " + wordle.getNumGuesses());
    }

    @Test
    void testTight_iterative_badSeed() {
        String answer = "tight";
        Wordle wordle = new Wordle(answer);
        Solver solver = new IterativeSolver(Collections.singletonList("light"));
        assertEquals(answer, solver.solve(wordle));
        System.out.println("tight (iterative, bad seed): " + wordle.getNumGuesses());
    }
}
