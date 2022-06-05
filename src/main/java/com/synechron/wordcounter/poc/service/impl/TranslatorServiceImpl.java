package com.synechron.wordcounter.poc.service.impl;

import com.synechron.wordcounter.poc.service.TranslatorService;
import org.springframework.stereotype.Service;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    @Override
    public String translateToEnglish(String words) {
        // here we use external Translator class or call external microservice to translate words
        return words;
    }
}
