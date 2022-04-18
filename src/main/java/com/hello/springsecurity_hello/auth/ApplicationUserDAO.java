package com.hello.springsecurity_hello.auth;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<ApplicationUser> selectApplictaionUserByUserName(String name);
}
