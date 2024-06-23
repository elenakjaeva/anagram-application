package com.beyonnex.anagram.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnagramInputIsNullException extends RuntimeException {

    public AnagramInputIsNullException(String input1, String input2) {
        super(getMessage(input1, input2));
        log.error(getMessage(input1, input2));
    }

    private static String getMessage(String input1, String input2) {
        return String.format("The input %s, %s for checking if two strings are anagrams is null", input1, input2);
    }
}
