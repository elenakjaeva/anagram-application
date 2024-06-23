package com.beyonnex.anagram.infrastructure.adapter.out.persitence.inmemory.config;

import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import com.beyonnex.anagram.infrastructure.adapter.out.persitence.inmemory.adapter.AnagramInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnagramInMemoryConfig {

    @Bean
    public AnagramRepository anagramRepository() {
        return new AnagramInMemoryRepository();
    }
}
