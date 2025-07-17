package com.georgievl.studentcourseenrollmentsystem.controllers;

import com.georgievl.studentcourseenrollmentsystem.models.Course;
import com.georgievl.studentcourseenrollmentsystem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        return this.courseService.getCourseById(id).isPresent()
                ? ResponseEntity.ok(this.courseService.getCourseById(id).get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return this.courseService.addCourse(course).map(addedCourse -> {
            URI location = URI.create("/api/v1/courses/" + addedCourse.getUuid());
            return ResponseEntity.created(location).body(addedCourse);
        }).orElse(ResponseEntity.badRequest().build());
    }
}
