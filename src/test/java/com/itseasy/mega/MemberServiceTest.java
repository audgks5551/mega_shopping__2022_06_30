package com.itseasy.mega;

import com.itseasy.mega.member.dto.MemberDto;
import com.itseasy.mega.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void 회원_생성() {
        // given
        MemberDto memberDto = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());

        // when
        MemberDto createdMemberDto = memberService.create(memberDto);

        // then
        assertThat(createdMemberDto.getId()).isNotNull();
    }

    @Test
    public void 회원_단건_조회() {
        // given
        MemberDto memberDto = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());
        MemberDto createdMemberDto = memberService.create(memberDto);

        // when
        MemberDto foundMemberDto = memberService.inquiryById(createdMemberDto.getId());

        // then
        assertThat(foundMemberDto).isEqualTo(createdMemberDto);
    }

    @Test
    public void 회원_전체_조회() {
        // given
        MemberDto memberDto1 = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());
        MemberDto memberDto2 = new MemberDto(
                "user2", "passwd2", "유저2", LocalDateTime.now(), LocalDateTime.now());
        memberService.create(memberDto1);
        memberService.create(memberDto2);

        // when
        List<MemberDto> foundMemberDtoList = memberService.inquiryAll();

        // then
        assertThat(foundMemberDtoList.size()).isEqualTo(2);
    }

    @Test
    public void 회원의_아이디가_중복_제거() {
        // given
        MemberDto memberDto1 = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());
        MemberDto memberDto2 = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());

        // when
        memberService.create(memberDto1);

        // then
        /**
         * 데이터 무결성 위반 예외
         */
        assertThatThrownBy(() -> memberService.create(memberDto2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void 회원의_이름_수정() {
        // given
        MemberDto memberDto1 = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());
        MemberDto createdMemberDto = memberService.create(memberDto1);

        // when
        createdMemberDto.setName("유저1_수정");
        memberService.modify(createdMemberDto);

        // then
        MemberDto foundMemberDto = memberService.inquiryById(createdMemberDto.getId());
        assertThat(foundMemberDto).isEqualTo(createdMemberDto);
    }

    @Test
    public void 회원_삭제() {
        // given
        MemberDto memberDto1 = new MemberDto(
                "user1", "passwd1", "유저1", LocalDateTime.now(), LocalDateTime.now());
        MemberDto createdMemberDto = memberService.create(memberDto1);

        // when
        memberService.withdrawal(createdMemberDto.getId());

        // then
        int memberListSize = memberService.inquiryAll().size();
        assertThat(memberListSize).isZero();
    }
}
