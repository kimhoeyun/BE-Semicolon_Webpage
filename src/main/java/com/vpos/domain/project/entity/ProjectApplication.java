package com.vpos.domain.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class ProjectApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String motivation;

    private String portfolio;

    @ElementCollection
    private List<String> tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder
    public ProjectApplication(String name, String phoneNumber, String motivation,
                            String portfolio, List<String> tool) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.motivation = motivation;
        this.portfolio = portfolio;
        this.tool = tool;
    }

    protected ProjectApplication() {}
}
