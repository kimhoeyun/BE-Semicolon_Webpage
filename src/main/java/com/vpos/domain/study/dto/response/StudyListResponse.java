package com.vpos.domain.study.dto.response;

import java.time.LocalDate;

public record StudyListResponse(
        Long id,
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate studyStart,
        LocalDate studyEnd
) {}