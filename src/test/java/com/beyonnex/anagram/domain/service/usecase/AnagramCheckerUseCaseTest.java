package com.beyonnex.anagram.domain.service.usecase;

import com.beyonnex.anagram.domain.exception.AnagramInputIsNullException;
import com.beyonnex.anagram.domain.model.Anagram;
import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link AnagramCheckerUseCase}
 */
@ExtendWith(MockitoExtension.class)
class AnagramCheckerUseCaseTest {

    @Mock
    private AnagramRepository repository;

    @InjectMocks
    private AnagramCheckerUseCase anagramCheckerUseCase;

    public static Stream<Arguments> testMultipleValues() {
        Arguments test1 = Arguments.of("test1", "test1", true);
        Arguments test2 = Arguments.of("abbcc", "ccabb", true);
        Arguments test3 = Arguments.of("test1", "test2", false);
        Arguments test4 = Arguments.of("clock1", "c1lock", true);

        return Stream.of(test1, test2, test3, test4);
    }

    @ParameterizedTest
    @MethodSource("testMultipleValues")
    void should_return_if_Anagram_when_isAnagram(String input1, String input2, boolean result) {
        boolean isAnagram = anagramCheckerUseCase.areAnagrams(input1, input2);

        Assertions.assertEquals(result, isAnagram);
        verify(repository, times(2)).save(any(Anagram.class));
    }

    @Test
    void should_throw_RentedVideoDoesNotExistException_when_execute() {
        String test = "test";

        AnagramInputIsNullException rentedVideoDoesNotExistException = assertThrows(
                AnagramInputIsNullException.class, () -> anagramCheckerUseCase.areAnagrams(null, test));

        assertTrue(rentedVideoDoesNotExistException.getMessage()
                .contains(test));
        assertTrue(rentedVideoDoesNotExistException.getMessage()
                .contains("null"));
    }
}