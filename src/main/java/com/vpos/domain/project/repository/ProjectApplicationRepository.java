package com.vpos.domain.project.repository;

import com.vpos.domain.project.entity.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {

    List<ProjectApplication> findAllByUserId(Long userId);
}