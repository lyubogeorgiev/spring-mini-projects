package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Enrollment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentService {
    Optional<Enrollment> enrollStudentInCourse(UUID studentId, UUID courseId);
    List<Enrollment> getStudentEnrollments(UUID studentId);
    Boolean deleteStudentEnrollments(UUID studentId);
}
