package com.beyonnex.anagram.domain.config;

import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import com.beyonnex.anagram.domain.service.port.out.AnagramRepository;
import com.beyonnex.anagram.domain.service.usecase.AnagramCheckerUseCase;
import com.beyonnex.anagram.domain.service.usecase.GetAnagramUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnagramBeanConfiguration {

    @Bean
    public AnagramChecker anagramChecker(AnagramRepository repository) {
        return new AnagramCheckerUseCase(repository);
    }

    @Bean
    public GetAnagram getAnagram(AnagramRepository repository) {
        return new GetAnagramUseCase(repository);
    }
}
