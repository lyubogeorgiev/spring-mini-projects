package com.georgievl.bookcatalogapi.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int year_published;
}
