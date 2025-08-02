package com.vpos.domain.study.controller;

import com.vpos.domain.study.dto.response.StudyDetailResponseDto;
import com.vpos.domain.study.dto.response.StudyListResponseDto;
import com.vpos.domain.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}