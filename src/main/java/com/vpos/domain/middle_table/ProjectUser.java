package com.vpos.domain.middle_table;

import com.vpos.domain.project.entity.Project;
import com.vpos.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}