package com.georgievl.bookcatalogapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int year;
}
