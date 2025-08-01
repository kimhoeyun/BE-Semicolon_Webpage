package com.vpos.domain.user.controller;

import com.vpos.domain.user.dto.request.SignUpRequestDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto userRequest) {
        User user = signUpService.register(userRequest);
        return ResponseEntity.ok("회원가입 성공: " + user.getUserId());
    }
}