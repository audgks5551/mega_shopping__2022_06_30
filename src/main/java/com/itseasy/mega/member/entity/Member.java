package com.itseasy.mega.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @Setter(NONE)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
}
