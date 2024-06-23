package com.beyonnex.anagram.infrastructure.adapter.in.restapi.controller;

import com.beyonnex.anagram.domain.exception.AnagramInputIsNullException;
import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link AnagramController}
 */
@WebMvcTest(AnagramController.class)
class AnagramControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnagramChecker anagramChecker;

    @MockBean
    private GetAnagram getAnagram;

    @Test
    void should_return_Yes_when_areAnagrams_when_input_is_true() throws Exception {
        Mockito.when(anagramChecker.areAnagrams("listen", "silent")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/anagrams")
                        .param("input1", "listen")
                        .param("input2", "silent"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Yes")));
    }

    @Test
    void should_return_Yes_when_areAnagrams_when_input_is_false() throws Exception {
        Mockito.when(anagramChecker.areAnagrams("listen", "silent1")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/anagrams")
                        .param("input1", "listen")
                        .param("input2", "silent1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No")));
    }

    @Test
    void should_return_Bad_Request_when_areAnagrams_throws_Exception() throws Exception {
        Mockito.when(anagramChecker.areAnagrams(null, "silent")).thenThrow(new AnagramInputIsNullException(null, "silent"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/anagrams")
                        .param("input2", "silent"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getAllAnagrams() throws Exception {
        Mockito.when(getAnagram.findByValue("listen")).thenReturn(new String[]{"stenli", "enlist"});

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/anagrams/listen"))
                .andExpect(status().isOk());
    }
}