package com.example.kitchensink.controller;

import com.example.kitchensink.exception.MemberNotFoundException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberUIController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/ui/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/ui/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/ui/register")
    public String registerMember(Member member, Model model) {
        try {
            memberService.register(member);
            model.addAttribute("message", "Registration successful");
        } catch (Exception e) {
            model.addAttribute("message", "Registration failed: " + e.getMessage());
        }
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/ui/member")
    public String getMemberById(@RequestParam String id, Model model) {
        try {
            Member member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            return "member";
        } catch (MemberNotFoundException e) {
            model.addAttribute("message", "Member not found: " + e.getMessage());
            return "error";
        }
    }
}