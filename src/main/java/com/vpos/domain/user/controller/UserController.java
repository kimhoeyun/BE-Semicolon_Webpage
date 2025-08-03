package com.vpos.domain.user.controller;

import com.vpos.domain.user.dto.request.LoginRequestDto;
import com.vpos.domain.user.dto.request.SignUpRequestDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.service.SignUpService;
import com.vpos.global.jwt.dto.response.JwtResponseDto;
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

    @GetMapping("/login")
    public ResponseEntity<JwtResponseDto> signup(@RequestBody LoginRequestDto loginRequest) {
        JwtResponseDto jwtResponseDto = signUpService.login(loginRequest);
        return ResponseEntity.ok(jwtResponseDto);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("good");
    }

}