package com.wimp.customer.dto;

import lombok.Getter;

@Getter
public class CustomerDeletionRequestDto {
    private Long id;
    private String email;
    private String password;
}
