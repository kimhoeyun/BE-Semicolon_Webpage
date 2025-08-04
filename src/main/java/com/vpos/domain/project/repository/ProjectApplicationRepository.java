package com.vpos.domain.project.repository;

import com.vpos.domain.project.entity.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
}