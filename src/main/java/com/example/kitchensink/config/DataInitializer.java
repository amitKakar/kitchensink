package com.example.kitchensink.config;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that initializes data in the MongoDB database when the application starts.
 * This class inserts a dummy member entry if the database is empty.
 */
@Configuration
public class DataInitializer {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Loads initial data into the MongoDB database when the application starts.
     * This method checks if the `MemberRepository` is empty and, if so, inserts a dummy member entry.
     *
     * @return a {@link CommandLineRunner} that loads initial data into the database
     */
    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            if (memberRepository.count() == 0) {
                Member member = new Member();
                member.setId("1");
                member.setName("John Doe");
                member.setEmail("john.doe@example.com");
                memberRepository.save(member);
            }
        };
    }
}