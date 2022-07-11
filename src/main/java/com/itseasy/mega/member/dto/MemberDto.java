package com.itseasy.mega.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public MemberDto(String username, String password, String name, LocalDateTime created, LocalDateTime modified) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.created = created;
        this.modified = modified;
    }
}
