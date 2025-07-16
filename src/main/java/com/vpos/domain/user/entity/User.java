package com.vpos.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, name = "devPart")
    private DevPart devpart;

    @Column(nullable = false, length = 2048, name = "portfolio")
    private String portfolio;

    @Column(nullable = false, length = 2048, unique = true, name = "email")
    private String email;

    @Column(nullable = false, length = 2048, name = "image")
    private String image;

    @Column(nullable = false, length = 2048, name = "introduction")
    private String introduction;

    @Column(nullable = false, name = "graduation")
    private Boolean graduation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;

    @Column(nullable = false, length = 50, name = "department")
    private String department;
}