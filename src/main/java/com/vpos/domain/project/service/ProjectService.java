package com.vpos.domain.project.service;

import com.vpos.domain.project.dto.request.ProjectApplyRequestDto;
import com.vpos.domain.project.dto.request.ProjectCreateRequestDto;
import com.vpos.domain.project.dto.response.ProjectDetailResponseDto;
import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.entity.Project;
import com.vpos.domain.project.entity.ProjectApplication;
import com.vpos.domain.project.repository.ProjectApplicationRepository;
import com.vpos.domain.project.repository.ProjectRepository;
import com.vpos.domain.user.entity.ApplyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectApplicationRepository projectApplicationRepository;

    public List<ProjectListResponseDto> viewProjectList() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectListResponseDto(
                        project.getId(),
                        project.getTitle(),
                        project.getPersonnel(),
                        project.getRecruitStart(),
                        project.getRecruitEnd(),
                        project.getProjectStart(),
                        project.getProjectEnd()
                ))
                .toList();
    }

    public ProjectDetailResponseDto viewProjectDetail(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트를 찾을 수 없습니다."));

        return new ProjectDetailResponseDto(
                project.getId(),
                project.getTitle(),
                project.getPersonnel(),
                project.getRecruitStart(),
                project.getRecruitEnd(),
                project.getProjectStart(),
                project.getProjectEnd(),
                project.getContent()
        );
    }

    @Transactional
    public Long createNewProject(ProjectCreateRequestDto projectCreateRequest) {
        Project project = Project.builder()
                .title(projectCreateRequest.title())
                .personnel(projectCreateRequest.personnel())
                .recruitStart(projectCreateRequest.recruitStart())
                .recruitEnd(projectCreateRequest.recruitEnd())
                .projectStart(projectCreateRequest.projectStart())
                .projectEnd(projectCreateRequest.projectEnd())
                .content(projectCreateRequest.content())
                .build();

        Project saved = projectRepository.save(project);
        return saved.getId();
    }


    @Transactional
    public Long applyProject(Long projectId, ProjectApplyRequestDto projectApplyRequest) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트를 찾을 수 없습니다."));

        ProjectApplication projectApplication = ProjectApplication.builder()
                .name(projectApplyRequest.name())
                .phoneNumber(projectApplyRequest.phoneNumber())
                .motivation(projectApplyRequest.motivation())
                .portfolio(projectApplyRequest.portfolio())
                .tool(projectApplyRequest.tool())
                .project(project)
                .build();

        return projectApplicationRepository.save(projectApplication).getId(); // 실제 저장 누락되어 있었음
    }

    @Transactional
    public void cancelProjectApplication(Long userId, Long applicationId) {
        ProjectApplication projectApplication = projectApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("신청 내역을 찾을 수 없습니다."));

        if (!projectApplication.getUserId().equals(userId)) {
            throw new AccessDeniedException("본인의 신청만 취소할 수 있습니다.");
        }

        if (projectApplication.getApplyStatus() != ApplyStatus.WAITING) {
            throw new IllegalStateException("대기 상태인 신청만 취소할 수 있습니다.");
        }

        projectApplicationRepository.delete(projectApplication);
    }
}