package com.synechron.wordcounter.poc.controller;

import com.synechron.wordcounter.poc.dto.InputDataDto;
import com.synechron.wordcounter.poc.service.WordCounterService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/word-counter", produces = MediaType.APPLICATION_JSON_VALUE)
public class WordCounterController {

    private final WordCounterService wordCounterService;

    //@PreAuthorize("hasAuthority('MODIFY')") //Authorization could be here
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    @ApiOperation(
            value = "Add one or mere words to WordCounter")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully added words"),
                    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                    @ApiResponse(
                            code = 403,
                            message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                    @ApiResponse(code = 500, message = "Internal error")
            })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>",
//                    required = true, dataTypeClass = String.class, paramType = "header") })
    public void addWords(@RequestBody @NonNull InputDataDto inputData) {
        log.debug("Adding words to WordCounter: " + inputData.getInputData());
        wordCounterService.addWords(inputData.getInputData());
    }

    /**
     * @param word
     * @return number of words found
     */
    @GetMapping(value = "/{word}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieve specific word information.",
            notes = "Returns counter of specific word.",
            response = Integer.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully retrieved number of words found."),
                    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                    @ApiResponse(
                            code = 403,
                            message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                    @ApiResponse(code = 500, message = "Internal error")
            })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>",
//                    required = true, dataTypeClass = String.class, paramType = "header") })
    public ResponseEntity<Integer> getWordCounter(
            @ApiParam(value = "Specific word to search.", required = true) @PathVariable("word")
                    String word) {
        log.debug(" ---> Get counter of word: " + word);
        return ResponseEntity.ok(wordCounterService.countNumberOfMatches(word));
    }
}
