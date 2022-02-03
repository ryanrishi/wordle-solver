package com.ryanrishi.wordle.solver;

import com.ryanrishi.wordle.LetterGuess;
import com.ryanrishi.wordle.Wordle;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Uses results of previous guess(es) and priority queue to determine which word to guess next
 * <p>
 * For example, if the solution is "perky" (2022-01-28), and a frequency dictionary says that "perky" has a higher frequency score than other /pe.ky/ words, then guess "perky" before guessing "peaky" or "pecky"
 */
public class IterativeSolverWithWordFrequency implements Solver {
    LinkedHashSet<String> futureGuesses = new LinkedHashSet<>();

    public IterativeSolverWithWordFrequency(Collection<String> seeds) {
        futureGuesses.addAll(seeds);
        try (InputStream is = IterativeSolverWithWordFrequency.class.getResourceAsStream("/ngrams_count_1w.txt")) {
            if (is == null) {
                throw new RuntimeException("Could not load file");
            }

            Scanner scanner = new Scanner(is);
            while (scanner.hasNext()) {
                String word = scanner.next().split("\\s+")[0];
                if (word.length() != 5) {
                    continue;
                }

                futureGuesses.add(word);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Error loading ngrams");
        }
    }

    public IterativeSolverWithWordFrequency() {
        this(Collections.emptySet());
    }

    @Override
    public String solve(Wordle wordle) {
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
                    .map(LetterGuess::getLetter)
                    .collect(Collectors.toSet());

            for (int i = 0; i < 5; i++) {
                LetterGuess letter = result.get(i);
                final int finalI = i;

                if (letter.isInWordAndInCorrectLocation()) {
                    // 🟩
                    lettersInSolution.add(letter.getLetter());
                    futureGuesses.removeIf(word -> word.charAt(finalI) != letter.getLetter());
                } else if (letter.isInWord()) {
                    // 🟨
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