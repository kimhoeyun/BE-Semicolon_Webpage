package com.vpos.domain.project.dto.request;

import java.util.List;

public record ProjectApplyRequestDto(
        String name,
        String phoneNumber,
        String motivation,
        String portfolio,
        List<String> tool
) {}