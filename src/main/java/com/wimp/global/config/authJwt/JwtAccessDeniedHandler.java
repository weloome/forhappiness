package com.wimp.global.config.authJwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wimp.global.dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException authException) throws IOException {
        // 필요한 권한이 존재하지 않는데 접근하려 할때 403(권한 오류)
        int responseStatus = HttpServletResponse.SC_FORBIDDEN;

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
