package com.vpos.domain.user.dto.response;

public record UserDetailResponseDto(
        String name,
        Enum role,
        Enum devPart,
        String phoneNumber,
        String introduction,
        String portfolio
) {}