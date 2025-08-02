package com.vpos.domain.study.dto.response;

import java.time.LocalDate;

public record StudyDetailResponseDto(
        Long id,
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate studyStart,
        LocalDate studyEnd,
        String content
) {}