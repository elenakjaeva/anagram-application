package com.beyonnex.anagram.infrastructure.adapter.in.cli;

import com.beyonnex.anagram.domain.service.port.in.AnagramChecker;
import com.beyonnex.anagram.domain.service.port.in.GetAnagram;
import com.beyonnex.anagram.infrastructure.adapter.in.dto.AnagramResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
@AllArgsConstructor
public class AnagramCli implements CommandLineRunner {

    private final AnagramChecker anagramChecker;

    private final GetAnagram getAnagram;

    @Override
    public void run(final String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            log.info("Choose an option: ");
            log.info("1. Check if two strings are anagrams");
            log.info("2. Find all anagrams of a string");
            log.info("3. Exit");

            int choice = 0;

            String line = scanner.nextLine();  // Consume newline

            try {
                choice = Integer.parseInt(line);
            } catch (Exception e) {
                log.error("Invalid input. Please choose between the offered options!!!");
            }

            switch (choice) {
                case 1:
                    log.info("Enter first string: ");
                    String str1 = scanner.nextLine();
                    log.info("Enter second string: ");
                    String str2 = scanner.nextLine();
                    boolean areAnagrams = anagramChecker.areAnagrams(str1, str2);
                    log.info("Are anagrams: " + AnagramResponse.of(areAnagrams).getResponse());
                    break;
                case 2:
                    log.info("Enter string to find anagrams: ");
                    String input = scanner.nextLine();
                    String[] anagrams = getAnagram.findByValue(input);
                    log.info("Anagrams for input value {} are : {}", input, Arrays.toString(anagrams));
                    break;
                case 3:
                    log.info("Exiting...");
                    scanner.close();
                    System.exit(0);
                    return;
                default:
                    log.info("Invalid choice, please try again.");
            }
        }
    }
}
