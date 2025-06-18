package com.georgievl.bookcatalogapi.reopsitories;

import com.georgievl.bookcatalogapi.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testSaveBook() {
        Book savedBook = bookRepository.save(Book.builder()
                .title("test")
                .author("test")
                .isbn("test")
                .year_published(1999)
                .build());
        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
    }
}
