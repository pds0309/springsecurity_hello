package com.hello.springsecurity_hello.security;

public enum ApplicationUserPermission {
    STUDENT_READ("stu_read"),
    STUDENT_WRITE("stu_write"),
    COURSE_READ("cor_read"),
    COURSE_WRITE("cor_write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
