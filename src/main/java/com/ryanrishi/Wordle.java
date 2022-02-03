package com.ryanrishi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
public class Wordle {
    public static final int WORD_LENGTH = 5;

    private final String answer;

    @Getter
    @Setter
    private boolean debug = true;

    @Getter
    private int numGuesses = 0;

    @Getter
    private boolean solved;

    public Wordle(final String answer) {
        this.answer = answer.toLowerCase();
    }

    public List<LetterGuess> guess(String guess) {
        if (guess.length() != WORD_LENGTH) {
            throw new BadGuessException(String.format("Guess must be %d characters long", WORD_LENGTH));
        }

        if (debug) {
            System.out.println(guess);
        }

        guess = guess.toLowerCase();
        ++numGuesses;

        if (answer.equals(guess)) {
            // victory
            solved = true;
            if (debug) {
                System.out.println("🟩🟩🟩🟩🟩");
            }

            return Collections.emptyList();
        }

        // create a map of letter in answer => number of times this letter occurs in the answer
        Map<Character, Integer> letterFrequencies = getLetterFrequencyMap();
        List<LetterGuess> result = new ArrayList<>();

        // handle letters in correct location first
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            LetterGuess letterGuess = new LetterGuess(c);

            if (c == answer.charAt(i)) {
                letterGuess.setInWord(true);
                letterGuess.setInWordAndInCorrectLocation(true);
                letterFrequencies.put(c, letterFrequencies.get(c) - 1);
            }

            result.add(letterGuess);
        }

        for (int i = 0; i < 5; i++) {
            char c = guess.charAt(i);

            if (letterFrequencies.getOrDefault(c, 0) > 0) {
                result.get(i).setInWord(true);
                letterFrequencies.put(c, letterFrequencies.get(c) - 1);
            }
        }

        if (debug) {
            StringBuilder sb = new StringBuilder();
            result.forEach(lg -> {
                if (lg.isInWordAndInCorrectLocation()) {
                    sb.append("🟩");
                } else if (lg.isInWord()) {
                    sb.append("🟨");
                } else {
                    sb.append("⬜️");
                }
            });
            System.out.println(sb);
        }

        return result;
    }

    private Map<Character, Integer> getLetterFrequencyMap() {
        Map<Character, Integer> letterFrequencies = new HashMap<>();
        for (char c : answer.toCharArray()) {
            letterFrequencies.putIfAbsent(c, 0);
            letterFrequencies.put(c, letterFrequencies.get(c) + 1);
        }

        return letterFrequencies;
    }
}
