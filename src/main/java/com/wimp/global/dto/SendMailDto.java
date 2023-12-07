package com.wimp.global.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMailDto {
    private String to;
    private String subject;
    private String text;
    private String from;
}
