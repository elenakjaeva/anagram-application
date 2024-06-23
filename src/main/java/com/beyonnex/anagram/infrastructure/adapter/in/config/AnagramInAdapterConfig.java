package com.beyonnex.anagram.infrastructure.adapter.in.config;

import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import com.beyonnex.anagram.infrastructure.adapter.in.cli.AnagramCli;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cli")
public class AnagramInAdapterConfig {

    @Bean
    public AnagramCli anagramCli(AnagramChecker anagramChecker, GetAnagram getAnagram) {
        return new AnagramCli(anagramChecker, getAnagram);
    }
}
