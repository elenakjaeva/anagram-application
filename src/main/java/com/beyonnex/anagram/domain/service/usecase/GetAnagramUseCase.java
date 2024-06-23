package com.beyonnex.anagram.domain.service.usecase;

import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import lombok.AllArgsConstructor;

/**
 * Gets all anagrams for an input String that were stored in the underlying storage
 */
@AllArgsConstructor
public class GetAnagramUseCase implements GetAnagram {
    private final AnagramRepository repository;

    @Override
    public String[] findByValue(final String value) {
        return repository.findByValue(value).toArray(String[]::new);
    }
}
