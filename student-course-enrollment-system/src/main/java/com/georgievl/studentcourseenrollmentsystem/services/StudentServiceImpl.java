package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService{
    private final List<Student> students = new ArrayList<>();

    @PostConstruct
    private void init() {
        students.addAll(List.of(Student.builder().firstName("John").lastName("Doe").build(),
                Student.builder().firstName("Jane").lastName("Doe").build(),
                Student.builder().firstName("Jane").lastName("Doe").build(),
                Student.builder().firstName("Jane").lastName("Doe").build()));
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Optional<Student> getStudentById(UUID uuid) {
        return students.stream()
                .filter(student -> student.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public Optional<Student> addStudent(Student student) {

        Student newStudent = Student.builder().firstName(student.getFirstName()).lastName(student.getLastName()).build();
        UUID newStudentId = newStudent.getUuid();
        students.add(newStudent);

        return students.stream()
                .filter(student1 -> student1.getUuid() == newStudentId)
                .findFirst();
    }
}
