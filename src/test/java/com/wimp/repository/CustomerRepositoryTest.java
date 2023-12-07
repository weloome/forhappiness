package com.wimp.repository;

import com.wimp.customer.dao.CustomerRepository;
import com.wimp.customer.model.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/customer-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByIdAndEmail_로_유저_데이터를_찾아올_수_있다() {
        // given
        // when
        Optional<CustomerEntity> result = customerRepository.findByIdAndEmail(1L, "db@test.com");
        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByIdAndEmail_은_데이터가_없으면_Optional_empty_를_내려준다() {
        // given
        // when
        Optional<CustomerEntity> result = customerRepository.findByIdAndEmail(20L, "db.l5@test.com");
        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void existsByEmail_은_이메일_유무를_반환한다() {
        // given
        String email = "dbs.l5@test.com";
        // when
        boolean exists = customerRepository.existsByEmail(email);
        // then
        assertThat(exists).isFalse();
    }
}
