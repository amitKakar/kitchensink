package com.example.kitchensink.service;

import com.example.kitchensink.model.Member;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MemberValidationTest {

    private final Validator validator;

    public MemberValidationTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testMemberNameWithNumber() {
        Member member = new Member("1", "John123", "john@gmail.com", "9876543210");

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertFalse(violations.isEmpty(), "Validation should fail for name containing numbers");
    }
}