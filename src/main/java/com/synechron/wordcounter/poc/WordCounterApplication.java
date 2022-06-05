package com.synechron.wordcounter.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.synechron.wordcounter")
@SpringBootApplication
public class WordCounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordCounterApplication.class, args);
    }

}
