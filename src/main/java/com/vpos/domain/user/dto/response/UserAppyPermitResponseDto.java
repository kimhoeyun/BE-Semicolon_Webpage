package com.vpos.domain.user.dto.response;

public record UserAppyPermitResponseDto(
        Long applicationId,
        String title,
        String type,     // "STUDY", "PROJECT"
        String status    // "WAITING", "APPROVED", "REJECTED"
) {}