package com.wimp.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CustomerModificationRequestDto {
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String fullName;
    @NotBlank
    private String phone;
    @Positive
    private Integer gender;
}
