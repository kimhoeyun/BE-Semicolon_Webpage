package com.vpos.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(unique = true, nullable = false, length = 2048, name = "email")
    private String email;

    @Column(unique = true, nullable = false, name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "devPart")
    private DevPart devpart;

    @Column(length = 2048, name = "portfolio")
    private String portfolio;

    @Column(length = 13, name = "phoneNumber")
    private String phoneNumber;

    @Column(length = 2048, name = "introduction")
    private String introduction;

    @Column(length = 50, name = "department")
    private String department;

    @Column(name = "graduation")
    private Boolean graduation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;
}