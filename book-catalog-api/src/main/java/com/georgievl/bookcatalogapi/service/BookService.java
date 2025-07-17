package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.model.BookDTO;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Map<UUID, BookDTO> getAllBooks();
    Optional<BookDTO> getBookById(UUID id);
    BookDTO addBook(BookDTO book);
    Optional<BookDTO> updateBook(UUID id, BookDTO book);
    BookDTO deleteBook(UUID id);
}
