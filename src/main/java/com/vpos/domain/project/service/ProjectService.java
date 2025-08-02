package com.vpos.domain.project.service;

import com.vpos.domain.project.dto.response.ProjectListResponseDto;
import com.vpos.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
