package com.synechron.wordcounter.poc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputDataDto {

    @ApiModelProperty(value = "Input data.")
    private String inputData;
}
