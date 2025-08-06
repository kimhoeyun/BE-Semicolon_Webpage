package com.vpos.domain.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Project {
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

    @Column(nullable = false, name = "projectStart")
    private LocalDate projectStart;

    @Column(nullable = false, name = "projectEnd")
    private LocalDate projectEnd;

    @Column(nullable = false)
    private Long writerId;

    @Builder
    public Project(String title, int personnel, String content,
                 LocalDate recruitStart, LocalDate recruitEnd,
                 LocalDate projectStart, LocalDate projectEnd, Long writerId) {
        this.title = title;
        this.personnel = personnel;
        this.content = content;
        this.recruitStart = recruitStart;
        this.recruitEnd = recruitEnd;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.writerId = writerId;
    }

    protected Project() {}
}