package com.vpos.domain.user.service;

import com.vpos.domain.user.dto.request.UserCreateRequestDto;
import com.vpos.domain.user.dto.request.UserUpdateRequestDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDetailResponseDto> viewUserList() {
        return userRepository.findAll().stream()
                .map(user -> new UserDetailResponseDto(
                        user.getName(),
                        user.getRole(),
                        user.getDevPart(),
                        user.getPhoneNumber(),
                        user.getIntroduction(),
                        user.getPortfolio()
                ))
                .toList();
    }

    public Long createNewUser(UserCreateRequestDto UserCreateRequest) {
        User user = User.builder()
                .name(UserCreateRequest.name())
                .graduation(UserCreateRequest.graduation())
                .devPart(UserCreateRequest.devPart())
                .phoneNumber(UserCreateRequest.phoneNumber())
                .portfolio(UserCreateRequest.portfolio())
                .introduction(UserCreateRequest.introduction())
                .build();

        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequestDto UserUpdateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));

        user.setName(UserUpdateRequest.name());
        user.setGraduation(UserUpdateRequest.graduation());
        user.setDevPart(UserUpdateRequest.devPart());
        user.setPhoneNumber(UserUpdateRequest.phoneNumber());
        user.setPortfolio(UserUpdateRequest.portfolio());
        user.setIntroduction(UserUpdateRequest.introduction());
    }
}
