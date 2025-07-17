package com.georgievl.studentcourseenrollmentsystem.models;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    private final UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
}
