package com.ryanrishi;

/**
 * Something that shouldn't stop the game, just something wrong with your guess.
 */
class BadGuessException extends IllegalArgumentException {
    public BadGuessException(String msg) {
        super(msg);
    }
}