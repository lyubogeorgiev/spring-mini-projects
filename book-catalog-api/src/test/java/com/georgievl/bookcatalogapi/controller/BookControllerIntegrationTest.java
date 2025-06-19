package com.georgievl.bookcatalogapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgievl.bookcatalogapi.entities.Book;
import com.georgievl.bookcatalogapi.mappers.BookMapper;
import com.georgievl.bookcatalogapi.model.BookDTO;
import com.georgievl.bookcatalogapi.reopsitories.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookControllerIntegrationTest {

    @Autowired
    BookController bookController;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Transactional
    @Rollback
    @Test
    void listAllBooks() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        List<Book> books = bookController.getBooks()
                .getBody().values()
                .stream().map(bookMapper::BookDTOToBook).toList();

        for (Book book : books) {
            System.out.println(book);
        }

        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void testEmptyResponse() {
        bookRepository.deleteAll();

        ResponseEntity<Map<UUID, BookDTO>> response = bookController.getBooks();

        assertEquals(Map.of(), response.getBody());
    }

    @Test
    void testBookId() {
        Book book = bookRepository.findAll().stream().findFirst().orElse(null);
        assertNotNull(book);

        ResponseEntity<BookDTO> response = bookController
                .getBookById(book.getId());

        assertNotNull(response.getBody());

        assertEquals(book.getId(), response.getBody().getId());
    }

    @Test
    void testBookIdNotFound() {
        assertThrows(NotFoundException.class,
                () -> bookController.getBookById(UUID.randomUUID().toString()));
    }
}