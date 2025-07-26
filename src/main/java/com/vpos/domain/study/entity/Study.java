package com.vpos.domain.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "title")
    private String title;

    @Column(nullable = false, name = "personnel")
    private int personnel;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "recruitStart")
    private LocalDate recruitStart;

    @Column(nullable = false, name = "recruitEnd")
    private LocalDate recruitEnd;

    @Column(nullable = false, name = "studyStart")
    private LocalDate studyStart;

    @Column(nullable = false, name = "studyEnd")
    private LocalDate studyEnd;
}
