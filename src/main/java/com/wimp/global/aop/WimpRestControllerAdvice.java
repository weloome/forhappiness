package com.wimp.global.aop;


import com.wimp.global.dto.ExceptionResponseDto;
import com.wimp.global.error.exception.WimpException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class WimpRestControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(WimpRestControllerAdvice.class);
//    private final MessageSource messageSource;

    private void log(final HttpServletRequest request, final Throwable cause) {
        logger.error("traceId : {}. error message : {}", request.getRequestId(), cause.getMessage(), cause);
    }

    @ExceptionHandler(WimpException.class)
    public ResponseEntity<ExceptionResponseDto> handleWimpException(final WimpException e, final HttpServletRequest request) {
        log(request, e);
        return new ResponseEntity<>(
                ExceptionResponseDto.builder()
                        .status(e.getHttpStatus().value())
                        .message(e.getErrorMessage())
                        .subCode(e.getSubCode())
                        .build(), e.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponseDto> handleException(final Exception e, final HttpServletRequest request) {
        log(request, e);
        return new ResponseEntity<>(
                ExceptionResponseDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ValidationException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidException(final Exception e, final HttpServletRequest request) {
        log(request, e);
        return new ResponseEntity<>(
                ExceptionResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage().substring(e.getMessage().lastIndexOf(" default message") + 1))
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
