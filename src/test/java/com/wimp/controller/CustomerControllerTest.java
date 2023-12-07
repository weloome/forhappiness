package com.wimp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wimp.customer.dto.CustomerModificationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "/sql/customer-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 에코_체크_응답이_200으로_내려온다() throws Exception {
        mockMvc.perform(get("/customer/echo").param("msg", "Hello World"))
                .andExpect(status().isOk());
    }

    @Test
    void 사용자는_특정_유저의_정보를_전달_받을_수_있다() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("db@test.com"));
    }

    @Test
    void 사용자는_존재하지_않는_유저의_아이디로_api_호출할_경우_500_응답을_받는다() throws Exception {
        Long customerId = 10000L;

        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("{\"status\":500,\"message\":\"존재하지 않는 회원입니다\",\"subCode\":null}"));
    }

    @Test
    void 사용자는_내_정보를_수정할_수_있다() throws Exception {
        Long customerId = 1L;
        CustomerModificationRequestDto customerModificationRequestDto = new CustomerModificationRequestDto();
        customerModificationRequestDto.setFirstName("bi");
        customerModificationRequestDto.setMiddleName("dam");
        customerModificationRequestDto.setLastName("bi");
        customerModificationRequestDto.setFullName("고양이는귀엽다");
        customerModificationRequestDto.setPhone("01088888887");

        mockMvc.perform(
                put("/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerModificationRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId));
    }
}
