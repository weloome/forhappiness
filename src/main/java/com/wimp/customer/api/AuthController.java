package com.wimp.customer.api;

import com.wimp.customer.application.CustomerService;
import com.wimp.customer.dto.CustomerLoginRequestDto;
import com.wimp.global.dto.JwtTokenInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final CustomerService customerService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/login")
    public JwtTokenInfo login(@RequestBody @Valid CustomerLoginRequestDto customerLoginRequestDto) {
        String email = customerLoginRequestDto.getEmail();
        String password = customerLoginRequestDto.getPassword();

        return customerService.login(email, password);
    }
}
