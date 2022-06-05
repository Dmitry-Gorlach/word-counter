package com.synechron.wordcounter.poc.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilTest {

    public static String transformToJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
