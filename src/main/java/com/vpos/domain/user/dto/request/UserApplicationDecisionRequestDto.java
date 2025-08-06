package com.vpos.domain.user.dto.request;

import com.vpos.domain.user.entity.ApplicationType;
import com.vpos.domain.user.entity.ApplyStatus;

public record UserApplicationDecisionRequestDto(
        ApplicationType type,
        ApplyStatus status
) {}