package com.vpos.global.email.dto;

import lombok.Getter;

public class EmailRequest {
    @Getter
    public static class VerificationEmailRequest {
        private String email;
    }

    @Getter
    public static class VerificationCodeRequest {
        private String email;
        private String code;
    }
}