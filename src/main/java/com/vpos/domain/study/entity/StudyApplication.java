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

    private Long userId;

    private String name;

    private String phoneNumber;

    private String motivation;

    private String portfolio;

    @ElementCollection
    private List<String> tool;

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus = ApplyStatus.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Builder
    public StudyApplication(Long userId, String name, String phoneNumber, String motivation,
                            String portfolio, List<String> tool) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.motivation = motivation;
        this.portfolio = portfolio;
        this.tool = tool;
        this.applyStatus = ApplyStatus.WAITING;
    }

    protected StudyApplication() {}

}