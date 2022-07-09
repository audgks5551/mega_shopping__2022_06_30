package com.itseasy.mega.member.repository;

import com.itseasy.mega.member.dto.MemberDto;
import com.itseasy.mega.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<MemberDto> findByUsername(String username);
}
