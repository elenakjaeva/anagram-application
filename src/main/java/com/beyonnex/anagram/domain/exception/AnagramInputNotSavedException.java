package com.beyonnex.anagram.domain.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class AnagramInputNotSavedException extends RuntimeException {

    public AnagramInputNotSavedException(String input) {
        super(getMessage(input));
        log.error(getMessage(input));
    }

    private static String getMessage(String input) {
        return String.format("Failed to save the anagram input %s", input);
    }
}
