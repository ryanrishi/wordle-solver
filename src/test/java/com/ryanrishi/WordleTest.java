package com.ryanrishi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordleTest {
    @Test
    void testVictoryOnFirstGuess() {
        Wordle wordle = new Wordle("robot");
        wordle.guess("robot");

        assertTrue(wordle.isSolved());
    }

    @Test
    void testDuplicateLetters_firstOfWhichInWrongSpotButBeforeNextLetter() {
        Wordle wordle = new Wordle("robot");
        List<LetterGuess> result = wordle.guess("trout");

        assertFalse(result.get(0).isInWord(), "because 't' is the last letter in the answer, the first 't' is not considered in the word");
    }

    @Test
    void testDuplicateLetters_secondOfWhichIsInRightSpot() {
        // TODO this tests the same thing as above - remove one case
        Wordle wordle = new Wordle("tight");
        List<LetterGuess> result = wordle.guess("hight");

        assertFalse(result.get(0).isInWord(), "should be ⬜️🟩🟩🟩🟩");
    }
}
