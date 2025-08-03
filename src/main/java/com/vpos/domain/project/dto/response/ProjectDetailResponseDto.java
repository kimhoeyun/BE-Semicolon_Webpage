package com.vpos.domain.project.dto.response;

import java.time.LocalDate;

public record ProjectDetailResponseDto(
        Long id,
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate studyStart,
        LocalDate studyEnd,
        String content
) {}