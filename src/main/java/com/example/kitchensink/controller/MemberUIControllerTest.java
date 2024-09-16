package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberUIControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberUIController memberUIController;

    @Test
    public void testGetAllMembers() {
        Member member1 = new Member("1", "John Doe","john@gmail.com","9876543210");
        Member member2 = new Member("2", "Jane Doe","jane@gmail.com","9876543211");

        when(memberService.getAllMembers()).thenReturn(Arrays.asList(member1, member2));

        Model model = new BindingAwareModelMap();
        String viewName = memberUIController.getAllMembers(model);

        assertEquals("members", viewName);
        List<Member> members = (List<Member>) model.getAttribute("members");
        assertEquals(2, members.size());
        assertEquals("John Doe", members.get(0).getName());
        assertEquals("Jane Doe", members.get(1).getName());
    }
}