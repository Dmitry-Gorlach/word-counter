package com.synechron.wordcounter.poc.service;

public interface WordCounterService {

    /**
     * @param words - words to add to the WordCounter
     * @throws IllegalArgumentException - trows an exception if word has non-alphabetic characters.
     */
    void addWords(String words) throws IllegalArgumentException;

    /**
     * @param word - template word for counting
     * @return - the number of identical words
     */
    int countNumberOfMatches(String word);
}
