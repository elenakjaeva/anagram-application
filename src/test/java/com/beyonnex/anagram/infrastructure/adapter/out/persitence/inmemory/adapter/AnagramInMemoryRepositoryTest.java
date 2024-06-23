package com.beyonnex.anagram.infrastructure.adapter.out.persitence.inmemory.adapter;

import com.beyonnex.anagram.domain.model.Anagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test for {@link AnagramInMemoryRepository}
 */
class AnagramInMemoryRepositoryTest {

    AnagramInMemoryRepository anagramRepository;

    @BeforeEach
    public void setUp() {
        anagramRepository = new AnagramInMemoryRepository();
    }

    public static Stream<Arguments> testMultipleValues() {
        Arguments test1 = Arguments.of(List.of("listen", "stenli", "aaaa", "enlist"), "listen", 2, List.of("stenli"));
        Arguments test2 = Arguments.of(List.of("country", "try", "counryt"), "counryt", 1, List.of("country"));
        Arguments test3 = Arguments.of(List.of("abcdef", "bcdef", "bbbb", "cccc"), "listen", 0, List.of());

        return Stream.of(test1, test2, test3);
    }

    @ParameterizedTest
    @MethodSource("testMultipleValues")
    void should_save_anagram_when_save_invoked_and_return_anagram_when_findByValue_invoked(List<String> inputArray, String value, int expectedResultSize, List<String> expectedResults) {
        inputArray.forEach(a -> anagramRepository.save(Anagram.of(a)));

        Set<String> anagrams = anagramRepository.findByValue(value);

        assertEquals(expectedResultSize, anagrams.size());

        expectedResults.forEach(result -> assertTrue(anagrams.contains(result)));
    }
}