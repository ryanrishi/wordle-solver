package com.ryanrishi;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LetterGuess {
    private @NonNull
    final char letter;
    private boolean isInWord = false;
    private boolean isInWordAndInCorrectLocation;
}
