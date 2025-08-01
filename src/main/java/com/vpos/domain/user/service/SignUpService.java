package com.vpos.domain.user.service;

import com.vpos.domain.user.dto.request.SignUpRequestDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.repository.UserRepository;
import com.vpos.global.email.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;

    @Transactional
    public User register(SignUpRequestDto signUpDto) {

        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입되어 있는 이메일입니다.");
        }

        if (userRepository.findByUserId(signUpDto.getUserId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        validateEmail(signUpDto.getEmail());

        validateUserId(signUpDto.getUserId());

        validatePassword(signUpDto.getPassword());

        User user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .userId(signUpDto.getUserId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수 입력 항목입니다.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
    }

    private void validateUserId(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("아이디는 필수 입력 항목입니다.");
        }

        if (userId.length() < 4 || userId.length() > 20) {
            throw new IllegalArgumentException("아이디는 4자 이상 20자 이하이어야 합니다.");
        }

        if (!userId.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("아이디는 영문, 숫자만 사용할 수 있습니다.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }

        String pwRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        if (!password.matches(pwRegex)) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이며, 영문, 숫자, 특수문자를 모두 포함해야 합니다.");
        }
    }
}