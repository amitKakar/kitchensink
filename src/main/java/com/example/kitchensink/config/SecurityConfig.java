package com.example.kitchensink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * Configures HTTP security and form login.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain.
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the {@link SecurityFilterChain} that was built
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/ui/member/**").hasRole("ADMIN") // Restrict access to admin users only
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/login").permitAll() // Allow access to static resources and login page
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirect to home page after login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    /**
     * Configures in-memory user details service.
     *
     * @return the {@link UserDetailsService} that was built
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("{noop}user") // {noop} is used to indicate that no password encoder is used
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build());
        return manager;
    }
}