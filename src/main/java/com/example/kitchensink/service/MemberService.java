package com.example.kitchensink.service;

import com.example.kitchensink.exception.MemberNotFoundException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
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
     * @return the member with the given ID
     * @throws MemberNotFoundException if no member is found with the given ID
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
     * @throws Exception if the email or phone number already exists
     */
    public void register(@Valid Member member) throws Exception {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new Exception("Email already exists");
        }
        if (memberRepository.existsByPhoneNumber(member.getPhoneNumber())) {
            throw new Exception("Phone number already exists");
        }
        memberRepository.save(member);
    }

    /**
     * Retrieves all members.
     *
     * @return a list of all members
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Updates an existing member.
     *
     * @param member the member to update
     * @throws Exception if the email or phone number already exists for another member
     */
    public void updateMember(@Valid Member member) throws Exception {
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + member.getId()));

        if (!existingMember.getEmail().equals(member.getEmail()) && memberRepository.existsByEmail(member.getEmail())) {
            throw new Exception("Email already exists");
        }
        if (!existingMember.getPhoneNumber().equals(member.getPhoneNumber()) && memberRepository.existsByPhoneNumber(member.getPhoneNumber())) {
            throw new Exception("Phone number already exists");
        }
        memberRepository.save(member);
    }

    /**
     * Deletes a member by their ID.
     *
     * @param id the ID of the member to delete
     * @throws MemberNotFoundException if no member is found with the given ID
     */
    public void deleteMemberById(String id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}