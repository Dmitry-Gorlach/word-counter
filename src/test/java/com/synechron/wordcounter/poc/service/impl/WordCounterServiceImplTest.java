package com.synechron.wordcounter.poc.service.impl;

import com.synechron.wordcounter.poc.service.TranslatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordCounterServiceImplTest {

    @InjectMocks
    WordCounterServiceImpl wordCounterService;

    @Mock
    TranslatorService translatorService;

    @Test
    void whenAddNullOrEmptyWords_thenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wordCounterService.addWords(null);
        });

        String expectedMessage = "Please write at least one word.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenNonAlphabeticalCharactersInWords_thenExceptionThrown() {
        when(translatorService.translateToEnglish(anyString())).thenReturn("hello");
        final String wrongWord = "&friends&";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wordCounterService.addWords("Hello my " + wrongWord);
        });

        String expectedMessage = String.format(
                "Word %s not added as it contains non-alphabet characters! Please try again.", wrongWord);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addWords() {
        when(translatorService.translateToEnglish("ciao")).thenReturn("hello");
        when(translatorService.translateToEnglish("hola")).thenReturn("hello");
        when(translatorService.translateToEnglish("salut")).thenReturn("hello");

        when(translatorService.translateToEnglish("mondo")).thenReturn("world");
        when(translatorService.translateToEnglish("mundo")).thenReturn("world");

        wordCounterService.addWords("ciao hola salut MondO muNdo");

        assertTrue(wordCounterService.getWordCounter().containsKey("hello"));
        assertEquals(Integer.valueOf(3), wordCounterService.getWordCounter().get("hello"));

        assertEquals(Integer.valueOf(2), wordCounterService.getWordCounter().get("world"));

    }

    @Test
    void whenCountEmptyWord_thenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wordCounterService.countNumberOfMatches("");
        });

        String expectedMessage = "Please write any word.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenCountMoreThanOneWords_thenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wordCounterService.countNumberOfMatches("Today is fine day");
        });

        String expectedMessage = "More that one word given. Write only one word without spaces, please.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void countNumberOfMatches() {
        wordCounterService.getWordCounter().clear();
        when(translatorService.translateToEnglish(anyString())).thenReturn("hello");

        wordCounterService.addWords("Chia hola Salut");

        verify(translatorService, times(3)).translateToEnglish(anyString());
        assertEquals(Integer.valueOf(3), wordCounterService.getWordCounter().get("hello"));
    }

}