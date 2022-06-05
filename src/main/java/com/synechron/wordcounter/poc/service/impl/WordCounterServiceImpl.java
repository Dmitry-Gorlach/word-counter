package com.synechron.wordcounter.poc.service.impl;


import com.synechron.wordcounter.poc.service.TranslatorService;
import com.synechron.wordcounter.poc.service.WordCounterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
@Getter
public class WordCounterServiceImpl implements WordCounterService {

    private static final String ONLY_ALPHABETICAL_CHARACTERS_PATTERN = "^[a-zA-Z]*$";

    private static final String SPACE_SEPARATOR_PATTERN = "\\s";

    private final Map<String, Integer> wordCounter = new ConcurrentHashMap<>();

    private final TranslatorService translatorService;


    @Override
    public void addWords(String words) throws IllegalArgumentException {
        checkAddWords(words);
        final String[] splitWords = splitWords(words);
        BiFunction<String, Integer, Integer> counter = (key, value) -> value + 1;

            Arrays.stream(splitWords)
                    .map(String::toLowerCase)
                    .filter(word -> {
                        if (!word.matches(ONLY_ALPHABETICAL_CHARACTERS_PATTERN)) {
                            throw new IllegalArgumentException(String.format(
                                    "Word %s not added as it contains non-alphabet characters! Please try again.", word));
                        }
                        return true;
                    })
                .map(translatorService::translateToEnglish)
                    .forEach(word -> {
                        if (wordCounter.containsKey(word)) {
                            wordCounter.computeIfPresent(word, counter);
                        } else {
                            wordCounter.put(word.toLowerCase(), 1);
                        }
                    });
    }

    @Override
    public int countNumberOfMatches(String word) {
        if (Objects.isNull(word) || word.isBlank()) {
            throw new IllegalArgumentException("Please write any word.");
        }

        String ignoreSpaceWord = word.toLowerCase();
        int countTokens = new StringTokenizer(ignoreSpaceWord).countTokens();

        if (countTokens > 1) {
            throw new IllegalArgumentException("More that one word given. Write only one word without spaces, please.");
        }

        return wordCounter.getOrDefault(ignoreSpaceWord, 0);
    }

    private void checkAddWords(String words) {
        if (Objects.isNull(words) || words.isBlank()) {
            throw new IllegalArgumentException("Please write at least one word.");
        }
    }

    private String[] splitWords(String words) {
        return words.split(SPACE_SEPARATOR_PATTERN);
    }
}
