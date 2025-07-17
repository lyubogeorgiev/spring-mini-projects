package com.georgievl.studentcourseenrollmentsystem.services;

import com.georgievl.studentcourseenrollmentsystem.models.Enrollment;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public Optional<Enrollment> enrollStudentInCourse(UUID studentId, UUID courseId) {
        Enrollment newEnrollment = Enrollment.builder().studentUuid(studentId).courseUuid(courseId).build();
        enrollments.add(newEnrollment);

        log.info("Enrollment added: {}", newEnrollment);
        for (Enrollment enrollment : enrollments) {
            log.info("Enrollment UUID: {}", enrollment.getUuid());
            log.info("Enrollment studentId: {}", enrollment.getStudentUuid());
            log.info("Enrollment courseId: {}", enrollment.getCourseUuid());
        }

        return enrollments.stream()
                .filter(enrollment -> enrollment.getUuid() == newEnrollment.getUuid())
                .findFirst();
    }

    @Override
    public List<Enrollment> getStudentEnrollments(UUID studentId) {
        return enrollments.stream().filter(enrollment -> enrollment.getStudentUuid().equals(studentId)).toList();
    }

    @Override
    public Boolean deleteStudentEnrollments(UUID studentId) {
        return enrollments.removeIf(enrollment -> enrollment.getStudentUuid().equals(studentId));
    }
}
