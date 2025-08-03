package com.vpos.domain.project.service;

import com.vpos.domain.project.dto.response.ProjectDetailResponseDto;
import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.entity.Project;
import com.vpos.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

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
}