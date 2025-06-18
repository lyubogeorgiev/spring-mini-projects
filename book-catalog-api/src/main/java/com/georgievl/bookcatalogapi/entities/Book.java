package com.georgievl.bookcatalogapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(name = "id", length = 36, columnDefinition = "varchar",
            nullable = false, unique = true, updatable = false)
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int year_published;
}
