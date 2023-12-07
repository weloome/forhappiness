package com.wimp.customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CustomerLoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
