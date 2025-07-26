package com.vpos.domain.middle_table;

import com.vpos.domain.study.entity.Study;
import com.vpos.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class StudyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}