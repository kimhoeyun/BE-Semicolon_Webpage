package com.vpos.domain.user.controller;

import com.vpos.domain.user.dto.request.LoginRequestDto;
import com.vpos.domain.user.dto.request.SignUpRequestDto;
import com.vpos.domain.user.dto.request.UserCreateRequestDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.service.SignUpService;
import com.vpos.domain.user.service.UserService;
import com.vpos.global.jwt.dto.response.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final SignUpService signUpService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto userRequest) {
        User user = signUpService.register(userRequest);
        return ResponseEntity.ok("회원가입 성공: " + user.getUserId());
    }

    @GetMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        JwtResponseDto jwtResponseDto = signUpService.login(loginRequest);
        return ResponseEntity.ok(jwtResponseDto);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("good");
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDetailResponseDto>> getUserList() {
        List<UserDetailResponseDto> userList = userService.viewUserList();
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody UserCreateRequestDto UserCreateRequest) {
        userService.createNewUser(UserCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}