package com.georgievl.bookcatalogapi.reopsitories;

import com.georgievl.bookcatalogapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
