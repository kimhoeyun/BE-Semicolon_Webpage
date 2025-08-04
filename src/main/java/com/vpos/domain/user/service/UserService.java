package com.vpos.domain.user.service;

import com.vpos.domain.user.dto.request.UserCreateRequestDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
