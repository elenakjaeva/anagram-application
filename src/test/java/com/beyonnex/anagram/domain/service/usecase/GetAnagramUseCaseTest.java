package com.beyonnex.anagram.domain.service.usecase;

import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Test for {@link GetAnagramUseCase}
 */
@ExtendWith(MockitoExtension.class)
class GetAnagramUseCaseTest {

    @Mock
    private AnagramRepository repository;

    @InjectMocks
    private GetAnagramUseCase getAnagramUseCase;

    @Test
    void should_return_result_when_findByValue_with_correct_input() {
        String input = "test1";

        when(repository.findByValue(input)).thenReturn(Set.of("te1st", "tes1t"));

        String[] anagrams = getAnagramUseCase.findByValue(input);

        Assertions.assertTrue(Arrays.asList(anagrams).contains("te1st"));
        Assertions.assertTrue(Arrays.asList(anagrams).contains("tes1t"));
        Assertions.assertFalse(Arrays.asList(anagrams).contains("qqqq"));
    }

    @Test
    void should_return_no_result_when_findByValue() {
        String input = "test1";
        String[] expectedResult = new String[]{};

        when(repository.findByValue(input)).thenReturn(Set.of());

        String[] anagrams = getAnagramUseCase.findByValue(input);

        Assertions.assertArrayEquals(expectedResult, anagrams);
    }
}