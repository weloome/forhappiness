package com.wimp.global.error.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class WimpException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final String subCode;

    public WimpException(
            final HttpStatus httpStatus,
            final String errorMessage,
            final String subCode,
            final Throwable cause) {
        super(errorMessage, cause);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.subCode = subCode;
    }

    public WimpException(
            final HttpStatus httpStatus,
            final String errorMessage,
            final String subCode
    ) {
        this(httpStatus, errorMessage, subCode, null);
    }

    public WimpException(
            final HttpStatus httpStatus,
            final String errorMessage) {
        this(httpStatus, errorMessage, null);
    }

}
