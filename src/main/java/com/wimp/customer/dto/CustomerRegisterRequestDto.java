package com.wimp.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
@Getter
@NoArgsConstructor
public class CustomerRegisterRequestDto {
    @Email @NotNull
    private String email;
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

    @Builder
    public CustomerRegisterRequestDto(String email, String password, String firstName, String middleName, String lastName, String fullName, String phone, Integer gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
    }
}
