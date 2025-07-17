package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.entities.Book;
import com.georgievl.bookcatalogapi.mappers.BookMapper;
import com.georgievl.bookcatalogapi.model.BookDTO;
import com.georgievl.bookcatalogapi.reopsitories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
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
        Book savedBook = this.bookRepository.save(bookMapper.BookDTOToBook(book));
        return bookMapper.BookToBookDTO(savedBook);
    }

    @Override
    public Optional<BookDTO> updateBook(UUID id, BookDTO book) {
        AtomicReference<Optional<BookDTO>> atomicReference = new AtomicReference<>();
        this.bookRepository.findById(id.toString()).map(foundBook -> {
            foundBook.setAuthor(book.getAuthor());
            foundBook.setTitle(book.getTitle());
//            Book editedBook = this.bookRepository.save(foundBook);
//            return bookMapper.BookToBookDTO(editedBook);
            atomicReference.set(Optional.of(bookMapper.BookToBookDTO(this.bookRepository.save(foundBook))));
        });

        return atomicReference.get();
    }

    @Override
    public BookDTO deleteBook(UUID id) {
        return null;
    }
}
