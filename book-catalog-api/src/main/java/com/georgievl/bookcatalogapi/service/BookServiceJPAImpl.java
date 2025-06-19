package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.mappers.BookMapper;
import com.georgievl.bookcatalogapi.model.BookDTO;
import com.georgievl.bookcatalogapi.reopsitories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BookServiceJPAImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Map<UUID, BookDTO> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(bookMapper::BookToBookDTO)
                .collect(Collectors.toMap(
                        book -> UUID.fromString(book.getId()),
                        book -> book
                ));
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.ofNullable(this.bookMapper
                .BookToBookDTO(this.bookRepository
                        .findById(id.toString()).orElse(null)));
    }

    @Override
    public BookDTO addBook(BookDTO book) {
        return null;
    }

    @Override
    public BookDTO updateBook(UUID id, BookDTO book) {
        return null;
    }

    @Override
    public BookDTO deleteBook(UUID id) {
        return null;
    }
}
