package com.wimp.customer.model;

import com.wimp.customer.dto.CustomerModificationRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name", nullable = false)
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String phone;
    private LocalDate dob;
    private Integer gender;
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setGender(Integer gender) { this.gender = gender; }
    public void setRole(Role role) { this.role = role; }

    public void updateFromCustomerDto(CustomerModificationRequestDto customerModificationRequestDto) {
        this.firstName = customerModificationRequestDto.getFirstName();
        this.middleName = customerModificationRequestDto.getMiddleName();
        this.lastName = customerModificationRequestDto.getLastName();
        this.fullName = customerModificationRequestDto.getFullName();
        this.phone = customerModificationRequestDto.getPhone();
        this.gender = customerModificationRequestDto.getGender();
    }
}
