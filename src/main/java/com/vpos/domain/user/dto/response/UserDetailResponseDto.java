package com.vpos.domain.user.dto.response;

import com.vpos.domain.user.entity.DevPart;
import com.vpos.domain.user.entity.Role;

public record UserDetailResponseDto(
        String name,
        Role role,
        DevPart devPart,
        String phoneNumber,
        String introduction,
        String portfolio
) {}