package com.beyonnex.anagram.infrastructure.adapter.in.restapi.controller;


import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import com.beyonnex.anagram.infrastructure.adapter.in.dto.AnagramResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling anagram strings
 */
@RestController
@RequestMapping("api/v1/anagrams")
@AllArgsConstructor
public class AnagramController {

    private final AnagramChecker anagramChecker;

    private final GetAnagram getAnagram;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AnagramResponse areAnagrams(@RequestParam String input1,
                                          @RequestParam String input2) {
        return AnagramResponse.of(anagramChecker.areAnagrams(input1, input2));
    }

    @GetMapping("/{value}")
    @ResponseStatus(HttpStatus.OK)
    public String[] getAllAnagrams(@PathVariable String value) {
        return getAnagram.findByValue(value);
    }
}
