package com.vpos.domain.study.service;

import com.vpos.domain.study.dto.response.StudyDetailResponseDto;
import com.vpos.domain.study.dto.response.StudyListResponseDto;
import com.vpos.domain.study.entity.Study;
import com.vpos.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public List<StudyListResponseDto> viewStudyList() {
        return studyRepository.findAll().stream()
                .map(study -> new StudyListResponseDto(
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

    public StudyDetailResponseDto viewStudyDetail(Long id) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 스터디를 찾을 수 없습니다."));

        return new StudyDetailResponseDto(
                study.getId(),
                study.getTitle(),
                study.getPersonnel(),
                study.getRecruitStart(),
                study.getRecruitEnd(),
                study.getStudyStart(),
                study.getStudyEnd(),
                study.getContent()
        );
    }
}