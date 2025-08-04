package com.vpos.domain.study.controller;

import com.vpos.domain.study.dto.request.StudyApplyRequestDto;
import com.vpos.domain.study.dto.request.StudyCreateRequestDto;
import com.vpos.domain.study.dto.response.StudyDetailResponseDto;
import com.vpos.domain.study.dto.response.StudyListResponseDto;
import com.vpos.domain.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping
    public ResponseEntity<List<StudyListResponseDto>> getStudyList() {
        List<StudyListResponseDto> studyList = studyService.viewStudyList();
        return ResponseEntity.ok(studyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyDetailResponseDto> getStudyDetail(@PathVariable Long id) {
        return ResponseEntity.ok(studyService.viewStudyDetail(id));
    }

    @PostMapping
    public ResponseEntity<Void> postStudy(@RequestBody StudyCreateRequestDto StudyCreateRequest) {
        studyService.createNewStudy(StudyCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @PostMapping("/{studyId}/apply")
    public ResponseEntity<Long> applyToStudy(@PathVariable Long studyId,
                                             @RequestBody @Valid StudyApplyRequestDto studyApplyRequest) {
        Long applicationId = studyService.applyStudy(studyId, studyApplyRequest);
        return ResponseEntity.ok(applicationId);
    }
}