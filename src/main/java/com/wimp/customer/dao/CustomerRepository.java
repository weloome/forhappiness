package com.wimp.customer.dao;

import com.wimp.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);

    Optional<CustomerEntity> findByIdAndEmail(Long id, String email);

    Optional<CustomerEntity> findByEmail(String email);
}
