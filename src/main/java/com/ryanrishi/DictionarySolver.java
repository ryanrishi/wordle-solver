package com.ryanrishi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public abstract class DictionarySolver implements Solver {
    Set<String> dictionary = new TreeSet<>();

    public DictionarySolver() {
        try (InputStream is = DictionarySolver.class.getResourceAsStream("/words_alpha.txt")) {
            if (is == null) {
                throw new RuntimeException("Could not load file");
            }

            Scanner scanner = new Scanner(is);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.length() != 5) {
                    continue;
                }

                dictionary.add(word);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Error loading dictionary");
        }
    }
}
