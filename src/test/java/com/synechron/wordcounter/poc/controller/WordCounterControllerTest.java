package com.synechron.wordcounter.poc.controller;

import com.synechron.wordcounter.poc.dto.InputDataDto;
import com.synechron.wordcounter.poc.service.WordCounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.synechron.wordcounter.poc.util.UtilTest.transformToJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(WordCounterController.class)
@AutoConfigureMockMvc
//@SpringBootTest
class WordCounterControllerTest {

    private static final String BASE_WORD_COUNTER_URI = "/api/word-counter";

    @MockBean
    WordCounterService wordCounterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddWords() throws Exception {
        String inputData = "Ciao hola salut";
        final InputDataDto inputDataDto = new InputDataDto();
        inputDataDto.setInputData(inputData);

        doNothing().when(wordCounterService).addWords(inputData);

        this.mockMvc.perform(post(BASE_WORD_COUNTER_URI + "/add")
                .content(transformToJsonString(inputData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnWordCounter() throws Exception {
        String words = "Ciao";
        int numberOfWords = 3;
        when(wordCounterService.countNumberOfMatches(any())).thenReturn(numberOfWords);

        String mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_WORD_COUNTER_URI + "/" + words))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(numberOfWords))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("3", mvcResult);
    }
}