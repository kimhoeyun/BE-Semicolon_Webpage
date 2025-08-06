package com.vpos.domain.user.service;

import com.vpos.domain.project.entity.ProjectApplication;
import com.vpos.domain.project.repository.ProjectApplicationRepository;
import com.vpos.domain.study.entity.StudyApplication;
import com.vpos.domain.study.repository.StudyApplicationRepository;
import com.vpos.domain.user.dto.request.UserCreateRequestDto;
import com.vpos.domain.user.dto.request.UserUpdateRequestDto;
import com.vpos.domain.user.dto.response.UserApplicationDetailResponseDto;
import com.vpos.domain.user.dto.response.UserApplyPermitResponseDto;
import com.vpos.domain.user.dto.response.UserDetailResponseDto;
import com.vpos.domain.user.entity.User;
import com.vpos.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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

    public List<UserApplyPermitResponseDto> getMyApplications(Long userId) {
        List<StudyApplication> studyApps = studyApplicationRepository.findAllByUserId(userId);
        List<ProjectApplication> projectApps = projectApplicationRepository.findAllByUserId(userId);

        List<UserApplyPermitResponseDto> result = new ArrayList<>();

        for (StudyApplication app : studyApps) {
            if (app.getStudy() != null) {
                result.add(new UserApplyPermitResponseDto(
                        app.getId(),
                        app.getStudy().getTitle(),
                        "STUDY",
                        app.getApplyStatus().name()
                ));
            }
        }

        for (ProjectApplication app : projectApps) {
            if (app.getProject() != null) {
                result.add(new UserApplyPermitResponseDto(
                        app.getId(),
                        app.getProject().getTitle(),
                        "PROJECT",
                        app.getApplyStatus().name()
                ));
            }
        }

        return result;
    }

    @Transactional(readOnly = true)
    public UserApplicationDetailResponseDto getMyStudyApplication(Long userId, Long applicationId) {
        StudyApplication studyApplication = studyApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("신청 내역을 찾을 수 없습니다."));

        if (!userId.equals(studyApplication.getUserId())) {
            throw new AccessDeniedException("본인의 신청서만 조회할 수 있습니다.");
        }

        return new UserApplicationDetailResponseDto(
                studyApplication.getName(),
                studyApplication.getPhoneNumber(),
                studyApplication.getPortfolio(),
                studyApplication.getTool(),
                studyApplication.getMotivation(),
                studyApplication.getApplyStatus(),
                "STUDY",
                studyApplication.getStudy().getTitle()
        );
    }

    @Transactional(readOnly = true)
    public UserApplicationDetailResponseDto getMyProjectApplication(Long userId, Long applicationId) {
        ProjectApplication projectApplication = projectApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("신청 내역을 찾을 수 없습니다."));

        if (!userId.equals(projectApplication.getUserId())) {
            throw new AccessDeniedException("본인의 신청서만 조회할 수 있습니다.");
        }

        return new UserApplicationDetailResponseDto(
                projectApplication.getName(),
                projectApplication.getPhoneNumber(),
                projectApplication.getPortfolio(),
                projectApplication.getTool(),
                projectApplication.getMotivation(),
                projectApplication.getApplyStatus(),
                "PROJECT",
                projectApplication.getProject().getTitle()
        );
    }
}