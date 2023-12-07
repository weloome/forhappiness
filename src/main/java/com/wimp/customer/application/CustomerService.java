package com.wimp.customer.application;

import com.wimp.customer.api.CustomerController;
import com.wimp.customer.dao.CustomerRepository;
import com.wimp.customer.dto.CustomerDeletionRequestDto;
import com.wimp.customer.dto.CustomerModificationRequestDto;
import com.wimp.customer.dto.CustomerRegisterRequestDto;
import com.wimp.customer.model.CustomerEntity;
import com.wimp.customer.model.Role;
import com.wimp.global.config.authJwt.JwtTokenProvider;
import com.wimp.global.dto.JwtTokenInfo;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional
    public Long create(CustomerRegisterRequestDto customerRegisterRequestDto) {
        if(customerRepository.existsByEmail(customerRegisterRequestDto.getEmail())) {
            throw new EntityExistsException("이미 존재하는 이메일입니다.");
        }

        CustomerEntity customer = CustomerEntity.builder()
                .email(customerRegisterRequestDto.getEmail())
                .firstName(customerRegisterRequestDto.getFirstName())
                .middleName(customerRegisterRequestDto.getMiddleName())
                .lastName(customerRegisterRequestDto.getLastName())
                .fullName(customerRegisterRequestDto.getFullName())
                .phone(customerRegisterRequestDto.getPhone())
                .dob(LocalDate.now())
                .gender(customerRegisterRequestDto.getGender())
                .passwordHash(hashPassword(customerRegisterRequestDto.getPassword()))
                .role(Role.USER)
                .build();

       return customerRepository.save(customer).getId();
    }

    public CustomerEntity getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
    }

    public CustomerEntity findCustomerInfoByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional
    public CustomerEntity update(String email, CustomerModificationRequestDto customerModificationRequestDto) {
        CustomerEntity customer = getById(findCustomerInfoByEmail(email).getId());
        customer.updateFromCustomerDto(customerModificationRequestDto);
        if(customerModificationRequestDto.getPassword() != null && !customerModificationRequestDto.getPassword().isEmpty()) {
            String newPassword = hashPassword(customerModificationRequestDto.getPassword());
            customer.setPasswordHash(newPassword);
        }
        return customer;
    }
    @Transactional
    public Boolean delete(CustomerDeletionRequestDto customerDeletionRequestDto) {
        Optional<CustomerEntity> customer = customerRepository.findByIdAndEmail(customerDeletionRequestDto.getId(), customerDeletionRequestDto.getEmail());
        if (customer.isEmpty()) return false;

        if (!passwordEncoder.matches(customerDeletionRequestDto.getPassword(), customer.get().getPasswordHash())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        customerRepository.deleteById(customerDeletionRequestDto.getId());
        return true;
    }

    @Transactional
    public ResponseEntity<String> deleteCustomerById(Long id) {
        CustomerEntity customer = getById(id);
        customerRepository.deleteById(id);
        return ResponseEntity.ok(customer.getEmail());
    }

    @Transactional
    public JwtTokenInfo login(String email, String password) {
        customerRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 계정입니다."));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.issueJwtToken(authentication);
    }
}
