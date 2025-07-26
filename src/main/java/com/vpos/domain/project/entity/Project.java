package com.vpos.domain.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "recruitStart")
    private LocalDate recruitStart;

    @Column(nullable = false, name = "recruitEnd")
    private LocalDate recruitEnd;

    @Column(nullable = false, name = "projectStart")
    private LocalDate projectStart;

    @Column(nullable = false, name = "projectEnd")
    private LocalDate projectEnd;
}