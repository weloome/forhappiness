package com.wimp.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class JwtTokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Date expiresIn;
    private Date freshExpiresIn;
//    private String scope;
}
