package com.example.kitchensink.controller;

import com.example.kitchensink.exception.MemberNotFoundException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class MemberUIController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // This should return the name of the login template
    }

    @GetMapping("/ui/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/ui/memberId")
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

    @GetMapping("/ui/member/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/ui/member/register")
    public String registerMember(@Valid Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("message", "Registration failed: " + result.getAllErrors().get(0).getDefaultMessage());
            return "register";
        }
        try {
            memberService.register(member);
            model.addAttribute("message", "Registration successful");
        } catch (Exception e) {
            model.addAttribute("message", "Registration failed: " + e.getMessage());
            return "error";
        }
        return "register";
    }

    @GetMapping("/ui/member/update")
    public String updateMember(@RequestParam String id, Model model) {
        try {
            Member member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            return "updateMember";
        } catch (MemberNotFoundException e) {
            model.addAttribute("message", "Member not found: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/ui/member/update")
    public String showUpdateForm(@Valid Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Update failed: " + result.getAllErrors().get(0).getDefaultMessage());
            return "updateMember";
        }
        try {
            memberService.updateMember(member);
            model.addAttribute("message", "Update successful");
        } catch (Exception e) {
            model.addAttribute("message", "Update failed: " + e.getMessage());
            return "error";
        }
        return "updateMember";
    }

    @PostMapping("/ui/member/delete")
    public String deleteMemberById(@RequestParam String id, Model model) {
        try {
            memberService.deleteMemberById(id);
            model.addAttribute("message", "Member deleted successfully");
        } catch (MemberNotFoundException e) {
            model.addAttribute("message", "Member not found: " + e.getMessage());
        }
        return "error";
    }
}