package com.vpos.domain.study.service;

import com.vpos.domain.study.dto.request.StudyApplyRequestDto;
import com.vpos.domain.study.dto.request.StudyCreateRequestDto;
import com.vpos.domain.study.dto.response.StudyDetailResponseDto;
import com.vpos.domain.study.dto.response.StudyListResponseDto;
import com.vpos.domain.study.entity.Study;
import com.vpos.domain.study.entity.StudyApplication;
import com.vpos.domain.study.repository.StudyApplicationRepository;
import com.vpos.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyApplicationRepository studyApplicationRepository;

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

    public Long createNewStudy(StudyCreateRequestDto studyCreateRequest) {
        Study study = Study.builder()
                .title(studyCreateRequest.title())
                .personnel(studyCreateRequest.personnel())
                .content(studyCreateRequest.content())
                .recruitStart(studyCreateRequest.recruitStart())
                .recruitEnd(studyCreateRequest.recruitEnd())
                .studyStart(studyCreateRequest.studyStart())
                .studyEnd(studyCreateRequest.studyEnd())
                .build();

        Study saved = studyRepository.save(study);
        return saved.getId();
    }

    @Transactional
    public Long applyStudy(Long studyId, StudyApplyRequestDto StudyApplyRequest) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new NoSuchElementException("해당 스터디를 찾을 수 없습니다."));

        StudyApplication studyApplication = StudyApplication.builder()
                .name(StudyApplyRequest.name())
                .phoneNumber(StudyApplyRequest.phoneNumber())
                .motivation(StudyApplyRequest.motivation())
                .portfolio(StudyApplyRequest.portfolio())
                .tool(StudyApplyRequest.tool())
                .study(study)
                .build();

        return studyApplicationRepository.save(studyApplication).getId();
    }
}