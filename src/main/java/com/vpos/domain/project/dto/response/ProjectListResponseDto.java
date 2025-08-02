package com.vpos.domain.project.dto.response;

import java.time.LocalDate;

public record ProjectListResponseDto (
        Long id,
        String title,
        int personnel,
        LocalDate recruitStart,
        LocalDate recruitEnd,
        LocalDate projectStart,
        LocalDate projectEnd
) {}