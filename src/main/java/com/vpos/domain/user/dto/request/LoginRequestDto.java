package com.vpos.domain.user.dto.request;

public record LoginRequestDto(
        String userId,
        String password
) {}