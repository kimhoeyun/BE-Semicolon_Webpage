package com.vpos.global.jwt.dto.response;

public record JwtResponseDto(
        String accessToken,
        String refreshToken
) {
}
