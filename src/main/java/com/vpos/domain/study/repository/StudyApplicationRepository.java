package com.vpos.domain.study.repository;

import com.vpos.domain.study.entity.StudyApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyApplicationRepository extends JpaRepository<StudyApplication, Long> {

    List<StudyApplication> findAllByUserId(Long userId);
}