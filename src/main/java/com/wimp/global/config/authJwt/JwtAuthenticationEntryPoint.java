package com.wimp.global.config.authJwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wimp.global.dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401(인증 실패)
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        int responseStatus = HttpServletResponse.SC_UNAUTHORIZED;

        response.setStatus(responseStatus);

        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .status(responseStatus).message(authException.getMessage()).build();

        String json = objectMapper.writeValueAsString(exceptionResponseDto);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }
}
