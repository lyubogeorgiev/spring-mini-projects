package com.georgievl.studentcourseenrollmentsystem.controllers;

import com.georgievl.studentcourseenrollmentsystem.models.Student;
import com.georgievl.studentcourseenrollmentsystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        return this.studentService.getStudentById(id).isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(this.studentService.getStudentById(id).get());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        return this.studentService.addStudent(student).map(addedStudent -> {
            URI location = URI.create("/api/v1/students/" + addedStudent.getUuid());
            return ResponseEntity.created(location).body(addedStudent);
        }).orElse(ResponseEntity.badRequest().build());
    }
}
