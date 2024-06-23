package com.beyonnex.anagram.domain.model;


import lombok.Value;

@Value(staticConstructor = "of")
public class Anagram {

    String value;

}
