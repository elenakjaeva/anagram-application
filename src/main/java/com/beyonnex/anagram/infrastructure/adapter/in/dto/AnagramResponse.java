package com.beyonnex.anagram.infrastructure.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnagramResponse {

    private String response;

    public static AnagramResponse of(boolean isAnagram) {
        if (isAnagram) {
            return new AnagramResponse("Yes");
        }
        return new AnagramResponse("No");
    }
}
