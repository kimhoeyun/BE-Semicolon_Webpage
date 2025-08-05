package com.vpos.domain.user.dto.request;

import com.vpos.domain.user.entity.DevPart;

public record UserUpdateRequestDto(
        String name,
        Boolean graduation,
        DevPart devPart,
        String phoneNumber,
        String portfolio,
        String introduction
) {}