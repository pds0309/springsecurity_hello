package com.hello.springsecurity_hello.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 인코딩에 bcrypt 를 사용하겠다.
        // if you do not encode password
        // BCryptPasswordEncoder: "Encoded password does not look like BCrypt"
        return new BCryptPasswordEncoder(10);
    }
}
