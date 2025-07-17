package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(UUID id);
    Optional<Course> addCourse(Course course);
}
