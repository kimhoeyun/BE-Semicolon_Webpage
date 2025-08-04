package com.vpos.domain.project.controller;

import com.vpos.domain.project.dto.request.ProjectCreateRequestDto;
import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProjectListResponseDto> getProjectDetail(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.viewProjectDetail(id));
    }

    @PostMapping
    public ResponseEntity<Void> postProject(@RequestBody ProjectCreateRequestDto ProjectCreateRequest) {
        projectService.createNewProject(ProjectCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }
}
