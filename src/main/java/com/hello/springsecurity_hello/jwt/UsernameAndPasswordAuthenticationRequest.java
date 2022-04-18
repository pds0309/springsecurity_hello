package com.hello.springsecurity_hello.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
