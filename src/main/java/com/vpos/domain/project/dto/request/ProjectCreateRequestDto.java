package com.vpos.domain.project.dto.request;

import java.time.LocalDate;

public record ProjectCreateRequestDto(
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate projectStart,
        LocalDate projectEnd,
        String content
) {}