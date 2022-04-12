package com.hello.springsecurity_hello.student;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


//role에 매핑된 permissions 들의 동작을 확인하자.

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1l, "김갑환"),
            new Student(2l, "최번개"),
            new Student(3l, "좡거한")
    );

    @GetMapping
    public List<Student> getList() {
        return STUDENTS;
    }

    @PostMapping
    public void register(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("{studentId}")
    public void delete(@PathVariable Long studentId) {
        System.out.println(studentId + "삭제");
    }

    @PutMapping("{studentId}")
    public void update(@PathVariable Long studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));
    }

}
