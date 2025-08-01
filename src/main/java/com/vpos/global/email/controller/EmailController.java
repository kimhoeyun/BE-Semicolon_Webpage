package com.vpos.global.email.controller;

import com.vpos.global.email.dto.EmailRequest;
import com.vpos.global.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class EmailController {
    private final EmailService emailService;

    /*
    인증 코드 전송
     */
    @PostMapping("/verification/email")
    public ResponseEntity<String> getEmailForVerification(@RequestBody EmailRequest.VerificationEmailRequest request) {
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.sendSimpleVerificationMail(request.getEmail(), requestedAt);
        return ResponseEntity.ok("인증 코드 전송 완료");
    }

    /*
    이메일 인증 확인
     */
    @PostMapping("/verification/code")
    public ResponseEntity<String> verificationByCode(@RequestBody EmailRequest.VerificationCodeRequest request) {
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.verifyCode(request.getEmail(), request.getCode(), requestedAt);
        return ResponseEntity.ok("이메일 인증 완료");
    }
}