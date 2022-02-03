package com.ryanrishi;

import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A solver that uses results of previous guess(es) to choose which words to try next
 */
@NoArgsConstructor
public class IterativeSolver extends DictionarySolver {
    Collection<String> seeds = Collections.emptySet();

    public IterativeSolver(Collection<String> seeds) {
        this.seeds = seeds;
    }

    @Override
    public String solve(Wordle wordle) {
        Set<String> futureGuesses = new LinkedHashSet<>(seeds);
        futureGuesses.addAll(dictionary);
        Set<Character> lettersNotInSolution = new HashSet<>();
        List<Character> lettersInSolution = new ArrayList<>();

        while (!wordle.isSolved() && !futureGuesses.isEmpty()) {
            String guess = futureGuesses.iterator().next();
            futureGuesses.remove(guess);

            List<LetterGuess> result = wordle.guess(guess);

            if (wordle.isSolved()) {
                return guess;
            }

            Set<Character> lettersInSolutionAndInCorrectLocation = result.stream()
                    .filter(LetterGuess::isInWordAndInCorrectLocation)
                    .map(lg -> lg.getLetter())
                    .collect(Collectors.toSet());

            for (int i = 0; i < 5; i++) {
                LetterGuess letter = result.get(i);
                final int finalI = i;

                if (letter.isInWordAndInCorrectLocation()) {
                    lettersInSolution.add(letter.getLetter());
                    futureGuesses.removeIf(word -> word.charAt(finalI) != letter.getLetter());
                } else if (letter.isInWord()) {
                    lettersInSolution.add(letter.getLetter());
                    futureGuesses.removeIf(word -> word.charAt(finalI) == letter.getLetter());  // since this letter is in the wrong spot
                } else {
                    // ⬜️
                    // TODO handle frequency of letters. For answer=perky, guess=gehey, this is saying the second 'e' is not in the word since it's only in "perky" once
                    if (!lettersInSolution.contains(letter.getLetter())) {
                        // TODO there's a smarter way to do this
                        lettersNotInSolution.add(letter.getLetter());
                    }
                }
            }

            // if solution is "feeds" and guess is "guess", the first "s" will return ⬜️, but there is still an "s" in the answer and in correct location
            lettersNotInSolution.removeAll(lettersInSolutionAndInCorrectLocation);

            if (!lettersInSolution.isEmpty() || !lettersNotInSolution.isEmpty()) {
                futureGuesses.removeIf(word -> {
                    Set<Character> lettersInWord = word.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());

                    if (lettersInWord.parallelStream().anyMatch(lettersNotInSolution::contains)) {
                        return true;
                    }

                    if (!lettersInWord.containsAll(lettersInSolution)) {
                        return true;
                    }

                    return false;
                });
            }
        }

        // couldn't solve the puzzle
        return null;
    }
}