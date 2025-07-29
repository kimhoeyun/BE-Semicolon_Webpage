package com.vpos.global.email.repository;

import com.vpos.global.email.entity.EmailVerification;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VerificationCodeRepository {

    private final Map<String, EmailVerification> repository = new ConcurrentHashMap<>();

    public EmailVerification save(EmailVerification emailVerification) {
        return repository.put(emailVerification.getEmail(), emailVerification);
    }

    public Optional<EmailVerification> findByEmail(String email) {
        return Optional.ofNullable(repository.get(email));
    }

    public void remove(EmailVerification emailVerification) {
        repository.remove(emailVerification.getEmail());
    }
}