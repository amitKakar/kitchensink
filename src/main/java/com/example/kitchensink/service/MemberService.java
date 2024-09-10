package com.example.kitchensink.service;

import com.example.kitchensink.exception.MemberNotFoundException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing members.
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Retrieves a member by their ID.
     *
     * @param id the ID of the member
     * @return the member with the specified ID
     * @throws MemberNotFoundException if no member is found with the specified ID
     */
    public Member getMemberById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new MemberNotFoundException("Member not found with id: " + id);
        }
    }

    /**
     * Registers a new member.
     *
     * @param member the member to register
     */
    public void register(Member member) {
        memberRepository.save(member);
    }

    /**
     *
     * @return
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}