package com.vpos.domain.study.dto.request;

import java.time.LocalDate;

public record StudyCreateRequestDto(
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate studyStart,
        LocalDate studyEnd,
        String content
) {}