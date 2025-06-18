package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.model.Book;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Map<UUID, Book> getAllBooks();
    Optional<Book> getBookById(UUID id);
    Book addBook(Book book);
    Book updateBook(UUID id, Book book);
    Book deleteBook(UUID id);
}
