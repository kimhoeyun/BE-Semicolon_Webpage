package com.vpos.domain.study.controller;

import com.vpos.domain.study.dto.response.StudyListResponse;
import com.vpos.domain.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private StudyService studyService;

    @GetMapping
    public ResponseEntity<List<StudyListResponse>> getStudyList() {
        List<StudyListResponse> studyList = studyService.viewStudyList();
        return ResponseEntity.ok(studyList);
    }
}