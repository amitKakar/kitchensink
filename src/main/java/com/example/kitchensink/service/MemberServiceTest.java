package com.example.kitchensink.service;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void testGetAllMembers() {
        Member member1 = new Member("1", "John Doe","john@gmail.com","9876543210");
        Member member2 = new Member("2", "Jane Doe","jane@gmail.com","9876543211");

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        List<Member> members = memberService.getAllMembers();
        assertEquals(2, members.size());
        assertEquals("John Doe", members.get(0).getName());
        assertEquals("Jane Doe", members.get(1).getName());
    }
}