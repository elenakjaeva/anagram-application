package com.beyonnex.anagram.domain.service.usecase;

import com.beyonnex.anagram.domain.exception.AnagramInputIsNullException;
import com.beyonnex.anagram.domain.exception.AnagramInputNotSavedException;
import com.beyonnex.anagram.domain.model.Anagram;
import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * Checks if two Strings are anagrams, and saves the input for future use
 */
@AllArgsConstructor
public class AnagramCheckerUseCase implements AnagramChecker {

    private final AnagramRepository repository;

    @Override
    public boolean areAnagrams(final String input1, final String input2) {
        if (Objects.isNull(input1) || Objects.isNull(input2)) {
            throw new AnagramInputIsNullException(input1, input2);
        }

        try {
            repository.save(Anagram.of(input1));
        } catch (Exception e) {
            throw new AnagramInputNotSavedException(input1);
        }

        try {
            repository.save(Anagram.of(input2));
        } catch (Exception e) {
            throw new AnagramInputNotSavedException(input2);
        }

        if (input1.length() != input2.length()) {
            return false;
        }

        char[] charArray1 = input1.toLowerCase().toCharArray();
        char[] charArray2 = input2.toLowerCase().toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }
}
