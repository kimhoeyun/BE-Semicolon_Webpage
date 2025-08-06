package com.vpos.domain.study.dto.response;

import com.vpos.domain.user.entity.ApplyStatus;

import java.util.List;

public record StudyApplicantResponseDto(
        String name,
        String phoneNumber,
        String portfolio,
        List<String> tool,
        String motivation,
        ApplyStatus applyStatus
) {}