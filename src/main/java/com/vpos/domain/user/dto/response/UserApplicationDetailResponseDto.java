package com.vpos.domain.user.dto.response;

import com.vpos.domain.user.entity.ApplyStatus;

import java.util.List;

public record UserApplicationDetailResponseDto (
        String name,
        String phoneNumber,
        String portfolio,
        List<String> tool,
        String motivation,
        ApplyStatus applyStatus,
        String type, // "STUDY" or "PROJECT"
        String title
) {}