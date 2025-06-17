package com.georgievl.bookcatalogapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgievl.bookcatalogapi.model.Book;
import com.georgievl.bookcatalogapi.service.BookService;
import com.georgievl.bookcatalogapi.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Autowired
    ObjectMapper objectMapper;

    BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setUp() {
        bookServiceImpl = new BookServiceImpl();
        bookServiceImpl.initSampleBooks();

        assertNotNull(bookServiceImpl.getAllBooks());
        assertFalse(bookServiceImpl.getAllBooks().isEmpty());
    }

    @Test
    void getBooks() {
        given(bookService.getAllBooks()).willReturn(bookServiceImpl.getAllBooks());

        try {
            mockMvc.perform(get("/api/books")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$.length()").value(bookServiceImpl.getAllBooks().size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBookByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/books/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBookById() throws Exception {

        Book testBook = bookServiceImpl.getAllBooks().values().stream().toList().get(0);

        if(testBook != null){
            given(bookService.getBookById(UUID.fromString(testBook.getId()))).willReturn(testBook);

            mockMvc.perform(get("/api/books/" + testBook.getId())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id", is(testBook.getId())))
                    .andExpect(jsonPath("$.title", is(testBook.getTitle())))
                    .andExpect(jsonPath("$.author", is(testBook.getAuthor())))
                    .andExpect(jsonPath("$.isbn", is(testBook.getIsbn())))
                    .andExpect(jsonPath("$.year", is(testBook.getYear())));
        }

    }

    @Test
    void createBook() throws Exception {

        Book testBook = bookServiceImpl.getAllBooks().values().stream().findFirst().isPresent()
                ? bookServiceImpl.getAllBooks().values().stream().findFirst().get()
                : null;

        assertNotNull(testBook);

        testBook.setId(null);

        given(bookService.addBook(any(Book.class)))
                .willReturn(bookServiceImpl.getAllBooks().values().stream().toList().get(1));

        mockMvc.perform(post("/api/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateBook() throws Exception {
        Book book = bookServiceImpl.getAllBooks().values().stream().toList().get(0);

        given(bookService.updateBook(any(UUID.class), any(Book.class))).willReturn(book);

        mockMvc.perform(put("/api/books/" + book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(book)))
                        .andExpect(status().isOk())
                                .andExpect(content().json(objectMapper.writeValueAsString(book)));

        verify(bookService).updateBook(any(UUID.class), any(Book.class));
    }

    @Test
    void deleteBook() throws Exception {
        Book book = bookServiceImpl.getAllBooks().values().stream().toList().get(0);

        given(bookService.deleteBook(any(UUID.class))).willReturn(book);

        mockMvc.perform(delete("/api/books/" + book.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);

        verify(bookService).deleteBook(captor.capture());

        assertThat(book.getId()).isEqualTo(captor.getValue().toString());
    }
}