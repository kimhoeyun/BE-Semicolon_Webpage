package com.vpos.domain.study.service;

import com.vpos.domain.study.dto.request.StudyApplyRequestDto;
import com.vpos.domain.study.dto.request.StudyCreateRequestDto;
import com.vpos.domain.study.dto.response.StudyDetailResponseDto;
import com.vpos.domain.study.dto.response.StudyListResponseDto;
import com.vpos.domain.study.entity.Study;
import com.vpos.domain.study.entity.StudyApplication;
import com.vpos.domain.study.repository.StudyApplicationRepository;
import com.vpos.domain.study.repository.StudyRepository;
import com.vpos.domain.user.entity.ApplyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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

    @Transactional
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

    @Transactional
    public void cancelStudyApplication(Long userId, Long applicationId) {
        StudyApplication studyApplication = studyApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("신청 내역을 찾을 수 없습니다."));

        if (!studyApplication.getUserId().equals(userId)) {
            throw new AccessDeniedException("본인의 신청만 취소할 수 있습니다.");
        }

        if (studyApplication.getApplyStatus() != ApplyStatus.WAITING) {
            throw new IllegalStateException("대기 상태인 신청만 취소할 수 있습니다.");
        }

        studyApplicationRepository.delete(studyApplication);
    }
}