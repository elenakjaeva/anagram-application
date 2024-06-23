package com.beyonnex.anagram.infrastructure.adapter.out.persitence.inmemory.adapter;

import com.beyonnex.anagram.domain.model.Anagram;
import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class AnagramInMemoryRepository implements AnagramRepository {

    private final Map<String, Set<String>> inMemoryStorage = new HashMap<>();

    @Override
    public void save(final Anagram anagram) {
        String anagramCharValue = getSortedString(anagram.getValue());

        inMemoryStorage.computeIfAbsent(anagramCharValue, a -> new HashSet<>()).add(anagram.getValue());
    }

    @Override
    public Set<String> findByValue(final String value) {
        String anagramCharValue = getSortedString(value);
        Set<String> anagrams = Optional.ofNullable(inMemoryStorage.get(anagramCharValue)).orElse(new HashSet<>());
        HashSet<String> result = new HashSet<>(anagrams);
        // Exclude the input value itself from the result set
        result.remove(value);
        return result;
    }

    private String getSortedString(String value) {
        char[] charArray = value.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
