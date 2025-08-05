package com.vpos.domain.user.service;

import com.vpos.domain.project.entity.ProjectApplication;
import com.vpos.domain.project.repository.ProjectApplicationRepository;
import com.vpos.domain.study.entity.StudyApplication;
import com.vpos.domain.study.repository.StudyApplicationRepository;
import com.vpos.domain.user.dto.request.UserCreateRequestDto;
import com.vpos.domain.user.dto.request.UserUpdateRequestDto;
import com.vpos.domain.user.dto.response.UserAppyPermitResponseDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StudyApplicationRepository studyApplicationRepository;
    private final ProjectApplicationRepository projectApplicationRepository;

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

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 회원이 존재하지 않습니다."));

        userRepository.delete(user);
    }

    public List<UserAppyPermitResponseDto> getMyApplications(Long userId) {
        List<StudyApplication> studyApps = studyApplicationRepository.findAllByUserId(userId);
        List<ProjectApplication> projectApps = projectApplicationRepository.findAllByUserId(userId);

        List<UserAppyPermitResponseDto> result = new ArrayList<>();

        for (StudyApplication app : studyApps) {
            result.add(new UserAppyPermitResponseDto(
                    app.getId(),
                    app.getStudy().getTitle(),
                    "STUDY",
                    app.getApplyStatus().name()
            ));
        }

        for (ProjectApplication app : projectApps) {
            result.add(new UserAppyPermitResponseDto(
                    app.getId(),
                    app.getProject().getTitle(),
                    "PROJECT",
                    app.getApplyStatus().name()
            ));
        }

        return result;
    }
}