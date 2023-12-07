package com.wimp.service;

import com.wimp.customer.application.CustomerService;
import com.wimp.customer.dto.CustomerModificationRequestDto;
import com.wimp.customer.dto.CustomerRegisterRequestDto;
import com.wimp.customer.model.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/customer-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getById는_유저를_찾아올_수_있다() {
        // given
        // when
        CustomerEntity result = customerService.getById(1L);
        // then
        assertThat(result.getFullName()) .isEqualTo("고양이는귀엽다");
    }

    @Test
    void getById는_존재하지_않는_유저는_찾아올_수_없다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            CustomerEntity result = customerService.getById(50L);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void customerRegisterRequestDto_를_이용하여_유저를_생성할_수_있다() {
        // given
        CustomerRegisterRequestDto customerRegisterRequestDto = CustomerRegisterRequestDto.builder()
                .email("db.l@tteesst.com")
                .password("1234")
                .firstName("bi")
                .middleName("dan")
                .lastName("lee")
                .fullName("lee dan bi")
                .phone("01099999998")
                .gender(1)
                .build();

        // when
        Long result = customerService.create(customerRegisterRequestDto);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void customerModificationRequestDto_를_이용하여_유저를_수정할_수_있다() {
        // given
        CustomerModificationRequestDto customerModificationRequestDto = new CustomerModificationRequestDto();
        customerModificationRequestDto.setFirstName("bi");
        customerModificationRequestDto.setMiddleName("dam");
        customerModificationRequestDto.setLastName("bi");
        customerModificationRequestDto.setFullName("고양이는귀엽다");
        customerModificationRequestDto.setPhone("01088888887");

        // when
        CustomerEntity customerEntity = customerService.update("db.l@tteesst.com", customerModificationRequestDto);

        // then
        assertThat(customerEntity.getId()).isNotNull();
        assertThat(customerEntity.getFullName()).isEqualTo("고양이는귀엽다");
        assertThat(customerEntity.getPhone()).isEqualTo("01088888887");
    }
}
