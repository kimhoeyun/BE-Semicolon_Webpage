package com.vpos.global.email.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EmailVerification {
    private String code;
    private String email;
    private LocalDateTime createdAt;
    private Integer expirationTimeInMinutes;

    public boolean isExpired(LocalDateTime verifiedAt) {
        LocalDateTime expireAt = createdAt.plusMinutes(expirationTimeInMinutes);
        return verifiedAt.isAfter(expireAt);
    }

    public String generateCode() {
        String formattedExpiredAt = createdAt
                .plusMinutes(expirationTimeInMinutes)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return String.format(
                """
                        [인증 코드] 
                        %s
                        인증 코드가 생성된 시간 : %s
                                """,
                code, formattedExpiredAt
        );
    }
}