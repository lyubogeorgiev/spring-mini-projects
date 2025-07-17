package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(UUID uuid);
    Optional<Student> addStudent(Student student);
}
