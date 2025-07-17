package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Course;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService{
    private final List<Course> courses = new ArrayList<>();

    @PostConstruct
    private void init() {
        courses.addAll(List.of(
                Course.builder().title("Java").description("Java programming language").instructor("John").build(),
                Course.builder().title("Python").description("Python programming language").instructor("Jane").build(),
                Course.builder().title("C#").description("C# programming language").instructor("Jane").build(),
                Course.builder().title("C++").description("C++ programming language").instructor("Jane").build()
        ));
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Optional<Course> getCourseById(UUID id) {
        return courses.stream().filter(course -> course.getUuid().equals(id)).findFirst();
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        Course newCourse = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .instructor(course.getInstructor()).build();
        UUID newCourseId = newCourse.getUuid();
        courses.add(newCourse);

        return this.getCourseById(newCourseId);
    }
}
