package com.georgievl.studentcourseenrollmentsystem.models;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Enrollment {
    private final UUID uuid = UUID.randomUUID();
    private UUID studentUuid;
    private UUID courseUuid;
    private final LocalDate enrollmentDate = LocalDate.now();
}
