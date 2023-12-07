package com.wimp.global.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponseDto {
    private Integer status;
    private String message;
    private String subCode;
}
