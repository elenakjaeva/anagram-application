package com.beyonnex.anagram.domain.service.port.out;

import com.beyonnex.anagram.domain.model.Anagram;

import java.util.Set;

public interface AnagramRepository {

    void save(Anagram anagram);

    Set<String> findByValue(String value);
}
