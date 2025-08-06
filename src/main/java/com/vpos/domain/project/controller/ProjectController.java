package com.vpos.domain.project.controller;

import com.vpos.domain.project.dto.request.ProjectApplyRequestDto;
import com.vpos.domain.project.dto.request.ProjectCreateRequestDto;
import com.vpos.domain.project.dto.response.ProjectApplicantResponseDto;
import com.vpos.domain.project.dto.response.ProjectDetailResponseDto;
import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.service.ProjectService;
import com.vpos.global.jwt.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectListResponseDto>> getProjectList() {
        List<ProjectListResponseDto> projectList = projectService.viewProjectList();
        return ResponseEntity.ok(projectList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailResponseDto> getProjectDetail(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.viewProjectDetail(id));
    }

    @PostMapping
    public ResponseEntity<Void> postProject(@RequestBody ProjectCreateRequestDto ProjectCreateRequest) {
        projectService.createNewProject(ProjectCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @PostMapping("/{projectId}/apply")
    public ResponseEntity<Long> applyToProject(@PathVariable Long projectId,
                                             @RequestBody @Valid ProjectApplyRequestDto ProjectApplyRequest) {
        Long applicationId = projectService.applyProject(projectId, ProjectApplyRequest);
        return ResponseEntity.ok(applicationId);
    }

    @DeleteMapping("/me/project-applications/{applicationId}")
    public ResponseEntity<Void> cancelMyProjectApplication(
            @PathVariable Long applicationId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        projectService.cancelProjectApplication(userDetails.getId(), applicationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/projects/{projectId}/applications/{applicationId}")
    public ResponseEntity<ProjectApplicantResponseDto> getProjectApplicationDetail(
            @PathVariable Long projectId,
            @PathVariable Long applicationId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                projectService.getApplicationReview(projectId, applicationId, userDetails.getId())
        );
    }
}