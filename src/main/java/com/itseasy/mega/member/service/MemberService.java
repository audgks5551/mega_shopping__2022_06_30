package com.itseasy.mega.member.service;

import com.itseasy.mega.member.dto.MemberDto;
import com.itseasy.mega.member.entity.Member;
import com.itseasy.mega.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper mapper;

    public MemberDto create(MemberDto memberDto) {

        Member member = mapper.map(memberDto, Member.class);

        memberRepository.save(member);

        memberDto.setId(member.getId());

        return memberDto;
    }

    public MemberDto inquiryById(Long id) {

        Member member = memberRepository.findById(id).orElse(null);

        MemberDto memberDto = mapper.map(member, MemberDto.class);

        return memberDto;
    }

    public List<MemberDto> inquiryAll() {

        return memberRepository.findAll().stream()
                .map(member -> mapper.map(member, MemberDto.class))
                .collect(toList());
    }

    public void modify(MemberDto memberDto) {

        Member foundMember = memberRepository.findById(memberDto.getId()).orElse(null);

        mapper.map(memberDto, foundMember);
    }

    public void withdrawal(Long id) {
        memberRepository.deleteById(id);
    }
}
