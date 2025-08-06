package com.vpos.domain.project.dto.response;

import com.vpos.domain.user.entity.ApplyStatus;

import java.util.List;

public record ProjectApplicantResponseDto(
        String name,
        String phoneNumber,
        String portfolio,
        List<String> tool,
        String motivation,
        ApplyStatus applyStatus
) {}