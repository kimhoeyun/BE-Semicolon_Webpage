package com.vpos.global.jwt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;


public class AuthDto {

    public record RefreshTokenWithExpiry(
            String token,
            Date expiry
    ) {}

    public record RefreshRequest(
            String refreshToken
    ) {}

    public record RefreshResponse(
            String accessToken,

            @Schema(description = "리프레시 토큰", required = true)
            String refreshToken
    ) {}
}
