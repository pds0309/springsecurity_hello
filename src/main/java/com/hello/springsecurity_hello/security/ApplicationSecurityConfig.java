package com.hello.springsecurity_hello.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.hello.springsecurity_hello.security.ApplicationUserRole.ADMIN;
import static com.hello.springsecurity_hello.security.ApplicationUserRole.STUDENT;

@Configuration
// 애플리케이션의 인증, 인가 처리에 대한 설정을 이 클래스에서 진행함을 설정한다.
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 어떤  요청에 대해서든 인증을을 진행할 것이다. basic 방법으로
         *
         * AntMatchers
         * 특정 url 을 whitelist 로 만들어 주는 것
         */
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                // 특정 자원에 대해서 특정한 권한이 있어야 접근할 수 있도록 설정할 수 있다.
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails kim = User.builder()
                .username("킴갑환")
                .password(passwordEncoder.encode("1234"))
                .roles(STUDENT.name())
                .build();

        UserDetails choi = User.builder()
                .username("최번개")
                .password(passwordEncoder.encode("1234"))
                .roles(ADMIN.name())
                .build();


        return new InMemoryUserDetailsManager(kim, choi);
    }

}
