package com.hello.springsecurity_hello.auth;

import com.google.common.collect.Lists;
import com.hello.springsecurity_hello.security.ApplicationUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplictaionUserByUserName(String name) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> name.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser("킴갑환", passwordEncoder.encode("1234"),
                        ApplicationUserRole.STUDENT.getGrantedAuthority(),
                        true, true, true, true),
                new ApplicationUser("최번개", passwordEncoder.encode("1234"),
                        ApplicationUserRole.ADMIN.getGrantedAuthority(),
                        true, true, true, true),
                new ApplicationUser("장거한", passwordEncoder.encode("1234"),
                        ApplicationUserRole.MANAGER.getGrantedAuthority(),
                        true, true, true, true));
    }

}
