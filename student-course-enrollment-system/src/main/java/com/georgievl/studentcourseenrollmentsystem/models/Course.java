package com.georgievl.studentcourseenrollmentsystem.models;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    private final UUID uuid = UUID.randomUUID();
    private String title;
    private String description;
    private String instructor;

}
