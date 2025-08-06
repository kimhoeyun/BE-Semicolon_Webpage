package com.vpos.domain.user.controller;

import com.vpos.domain.user.dto.request.*;
import com.vpos.domain.user.dto.response.UserApplicationDetailResponseDto;
import com.vpos.domain.user.dto.response.UserApplyPermitResponseDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.service.SignUpService;
import com.vpos.domain.user.service.UserService;
import com.vpos.global.jwt.CustomUserDetails;
import com.vpos.global.jwt.dto.response.JwtResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<Void> postUser(@RequestBody UserCreateRequestDto userCreateRequest) {
        userService.createNewUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/user")
    public ResponseEntity<Void> patchUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                             @RequestBody @Valid UserUpdateRequestDto userUpdateRequest) {
        Long userId = userDetails.getId();
        userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me/applications")
    public ResponseEntity<List<UserApplyPermitResponseDto>> getMyApplications(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(userService.getMyApplications(userId));
    }

    @GetMapping("/me/study-applications/{id}")
    public ResponseEntity<UserApplicationDetailResponseDto> getMyStudyApplication(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getMyStudyApplication(userDetails.getId(), id));
    }

    @GetMapping("/me/project-applications/{id}")
    public ResponseEntity<UserApplicationDetailResponseDto> getMyProjectApplication(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getMyProjectApplication(userDetails.getId(), id));
    }

    @PatchMapping("/{postId}/{applicationId}")
    public ResponseEntity<Void> decideApplication(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId,
            @PathVariable Long applicationId,
            @RequestBody @Valid UserApplicationDecisionRequestDto userApplicationDecisionRequest) {

        userService.decideApplication(userDetails.getId(), postId, applicationId, userApplicationDecisionRequest);
        return ResponseEntity.noContent().build();
    }
}