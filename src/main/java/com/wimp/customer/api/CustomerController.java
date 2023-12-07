package com.wimp.customer.api;

import com.wimp.customer.application.CustomerService;
import com.wimp.customer.dto.CustomerDeletionRequestDto;
import com.wimp.customer.dto.CustomerModificationRequestDto;
import com.wimp.customer.dto.CustomerRegisterRequestDto;
import com.wimp.customer.model.CustomerEntity;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/echo")
    public String echoParam(@RequestParam String msg) {
        return msg;
    }

    @PostMapping
    public CreateMemberResponse create(@RequestBody @Valid CustomerRegisterRequestDto customerRegisterRequestDto) {
        Long id = customerService.create(customerRegisterRequestDto);
        return new CreateMemberResponse(id);
    }

    @GetMapping("/me")
    public CustomerEntity currentUserName(Principal principal) {
        return customerService.findCustomerInfoByEmail(principal.getName());
    }

    @PutMapping("/me")
    public CustomerEntity update(@Valid @RequestBody CustomerModificationRequestDto customerModificationRequestDto, Principal principal) {
        String email = principal.getName();
        return customerService.update(email, customerModificationRequestDto);
    }

    @DeleteMapping()
    public Boolean delete(@RequestBody CustomerDeletionRequestDto customerDeletionRequestDto) {
        return customerService.delete(customerDeletionRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

    @Getter
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
