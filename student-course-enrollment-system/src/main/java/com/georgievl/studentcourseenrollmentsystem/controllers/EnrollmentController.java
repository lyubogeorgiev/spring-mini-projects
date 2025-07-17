package com.georgievl.studentcourseenrollmentsystem.controllers;

import com.georgievl.studentcourseenrollmentsystem.models.Enrollment;
import com.georgievl.studentcourseenrollmentsystem.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enrollments")
    public ResponseEntity<Enrollment> enrollStudentInCourse(@RequestParam UUID studentId, @RequestParam UUID courseId) {
        return enrollmentService.enrollStudentInCourse(studentId, courseId)
                .map(addedEnrollment -> {
                    URI location = URI.create("/api/v1/enrollments/" + addedEnrollment.getUuid());
                    return ResponseEntity.created(location).body(addedEnrollment);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/students/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable UUID id) {
        return ResponseEntity.ok(this.enrollmentService.getStudentEnrollments(id));
    }

    @DeleteMapping("/students/enrollments/{id}")
    public ResponseEntity<Void> deleteStudentEnrollments(@PathVariable UUID id) {
        Boolean isDeleted = this.enrollmentService.deleteStudentEnrollments(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
