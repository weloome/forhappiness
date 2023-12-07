package com.wimp.global.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ResultResponseDto {
    private Boolean result;

    public static ResultResponseDto fromBoolean(Boolean result){
        return ResultResponseDto.builder().result(result).build();
    }
}
