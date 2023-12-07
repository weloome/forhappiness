package com.wimp.global.error.exception;

import com.wimp.global.error.code.JwtErrorCode;

public class JwtException extends RuntimeException {
    private final JwtErrorCode jwtErrorCode;

    public JwtException(String message, JwtErrorCode errorCode) {
        super(message);
        this.jwtErrorCode = errorCode;
    }

    public JwtException(JwtErrorCode errorCode) {
        super(errorCode.getMessage());
        this.jwtErrorCode = errorCode;
    }

    public JwtErrorCode getErrorCode() {
        return jwtErrorCode;
    }
}
