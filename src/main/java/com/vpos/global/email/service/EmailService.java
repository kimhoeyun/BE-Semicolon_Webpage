package com.vpos.global.email.service;

import com.vpos.global.email.entity.EmailVerification;
import com.vpos.global.email.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${spring.mail.host}")
    private String host;
    private final JavaMailSender mailSender;
    private final VerificationCodeRepository verificationCodeRepository;

    public void sendSimpleVerificationMail(String to, LocalDateTime sentAt) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(host);
        mailMessage.setTo(to);
        mailMessage.setSubject(String.format("Email Verification For %s", to));

        EmailVerification emailVerification = generateVerificationCode(sentAt, to);
        verificationCodeRepository.save(emailVerification);

        String text = emailVerification.generateCode();
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }

    public void verifyCode(String email, String code,LocalDateTime verifiedAt) {
        EmailVerification emailVerification = verificationCodeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException());

        if (emailVerification.isExpired(verifiedAt)) {
            throw new IllegalArgumentException();
        }

        if (!emailVerification.getCode().equals(code)) {
            throw new IllegalArgumentException();
        }

        verificationCodeRepository.remove(emailVerification);
    }

    private EmailVerification generateVerificationCode(LocalDateTime sentAt, String to) {

        Random r = new Random();
        String code = "";
        for(int i = 0; i < 6; i++) {
            code += Integer.toString(r.nextInt(10));
        }

        log.info("이메일 인증 코드 발급: {}", code);
        return EmailVerification.builder()
                .code(code)
                .email(to)
                .createdAt(sentAt)
                .expirationTimeInMinutes(30)
                .build();
    }
}