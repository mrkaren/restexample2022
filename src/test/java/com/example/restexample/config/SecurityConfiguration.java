package com.example.restexample.config;


import com.example.restexample.model.Role;
import com.example.restexample.model.User;
import com.example.restexample.security.CurrentUser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SecurityConfiguration {
//
//    @Bean
//    @Primary
//    public UserDetailsService userDetailsService() {
//        User user = new User(1, "poxos", "poxosyan",
//                "poxos@mail.com", "poxos", Role.ADMIN);
//        CurrentUser basicUser = new CurrentUser(user);
//
//        return new InMemoryUserDetailsManager(Arrays.asList(basicUser));
//    }

}
