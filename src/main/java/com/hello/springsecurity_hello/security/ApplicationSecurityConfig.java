package com.hello.springsecurity_hello.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// 애플리케이션의 인증, 인가 처리에 대한 설정을 이 클래스에서 진행함을 설정한다.
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

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
                .antMatchers("/", "/index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
