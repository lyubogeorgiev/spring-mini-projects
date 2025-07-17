package com.georgievl.bookcatalogapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgievl.bookcatalogapi.entities.Book;
import com.georgievl.bookcatalogapi.mappers.BookMapper;
import com.georgievl.bookcatalogapi.model.BookDTO;
import com.georgievl.bookcatalogapi.reopsitories.BookRepository;
import com.georgievl.bookcatalogapi.service.BookService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookControllerIntegrationTest {

    @Autowired
    BookController bookController;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;
    @Qualifier("bookServiceJPAImpl")
    @Autowired
    private BookService bookService;

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

    @Rollback
    @Transactional
    @Test
    void saveNewBookTest() {
        BookDTO newBook = BookDTO.builder()
                .title("test")
                .author("test")
                .isbn("test")
                .year_published(1999)
                .build();

        ResponseEntity<BookDTO> response = bookController.createBook(newBook);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));

        System.out.println(response.getHeaders().getLocation());
//
        String[] uriParts = Objects.requireNonNull(response.getHeaders().getLocation()).toString().split("/");
        String bookId = uriParts[uriParts.length - 1];

        Book savedBook = bookMapper.BookDTOToBook(bookController.getBookById(bookId).getBody());

        assertNotNull(savedBook);


    }

    @Rollback
    @Transactional
    @Test
    void testBookUpdateById() {
        BookDTO bookDTO = bookService.getAllBooks().values().stream().findFirst().orElse(null);

        assertNotNull(bookDTO);
        String bookId = bookDTO.getId();

        bookDTO.setId(null);
        bookDTO.setAuthor("updated");
        bookDTO.setTitle("updated");

        ResponseEntity<BookDTO> response = bookController.updateBook(bookId, bookDTO);

        BookDTO updatedBookDTO = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertNotNull(updatedBookDTO);
        assertThat(updatedBookDTO.getAuthor()).isEqualTo(bookDTO.getAuthor());
        assertThat(updatedBookDTO.getTitle()).isEqualTo(bookDTO.getTitle());
    }
}