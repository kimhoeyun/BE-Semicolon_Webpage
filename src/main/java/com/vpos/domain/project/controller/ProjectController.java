package com.vpos.domain.project.controller;

import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
