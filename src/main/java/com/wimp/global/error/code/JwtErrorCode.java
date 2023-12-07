package com.wimp.global.error.code;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum JwtErrorCode {
    // Jwt
    JWT_INVALID_TOKEN(498, "JWT001", "Invalid JWT Token"),
    JWT_EXPIRED_TOKEN(401, "JWT002", "Expired JWT Token"),
    JWT_UNSUPPORTED_TOKEN(415, "JWT003", "Unsupported JWT Token"),
    JWT_CLAIMS_EMPTY(404, "JWT004", "JWT claims string is empty.");
    private final String code;
    private final String message;
    private final int status;

    JwtErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
