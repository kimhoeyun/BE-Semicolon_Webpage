package com.vpos.domain.study.dto.request;

import java.util.List;

public record StudyApplyRequestDto(
        String name,
        String phoneNumber,
        String motivation,
        String portfolio,
        List<String> tool
) {}