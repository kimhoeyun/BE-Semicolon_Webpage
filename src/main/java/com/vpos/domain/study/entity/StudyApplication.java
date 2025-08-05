package com.vpos.domain.study.entity;

import com.vpos.domain.user.entity.ApplyStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class StudyApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String motivation;

    private String portfolio;

    @ElementCollection
    private List<String> tool;

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Builder
    public StudyApplication(String name, String phoneNumber, String motivation,
                            String portfolio, List<String> tool) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.motivation = motivation;
        this.portfolio = portfolio;
        this.tool = tool;
    }

    protected StudyApplication() {}

}