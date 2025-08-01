package com.vpos.domain.study.service;

import com.vpos.domain.study.dto.response.StudyListResponse;
import com.vpos.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {

    private StudyRepository studyRepository;

    public List<StudyListResponse> viewStudyList() {
        return studyRepository.findAll().stream()
                .map(study -> new StudyListResponse(
                        study.getId(),
                        study.getTitle(),
                        study.getPersonnel(),
                        study.getRecruitStart(),
                        study.getRecruitEnd(),
                        study.getStudyStart(),
                        study.getStudyEnd()
                ))
                .toList();
    }
}