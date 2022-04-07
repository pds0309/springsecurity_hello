package com.hello.springsecurity_hello.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.hello.springsecurity_hello.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            COURSE_READ, COURSE_WRITE, STUDENT_READ
    ));

    private final Set<ApplicationUserPermission> permissionSet;


    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
