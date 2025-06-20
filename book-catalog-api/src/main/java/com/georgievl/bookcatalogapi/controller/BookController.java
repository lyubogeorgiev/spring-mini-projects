package com.georgievl.bookcatalogapi.controller;

import com.georgievl.bookcatalogapi.model.BookDTO;
import com.georgievl.bookcatalogapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Map<UUID, BookDTO>> getBooks(){
        return this.bookService.getAllBooks() != null ?
                ResponseEntity.ok(this.bookService.getAllBooks()) :
                ResponseEntity.notFound().build();
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Void> handleBooksNotFoundException(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id){

        return ResponseEntity
                .ok(this.bookService.getBookById(UUID.fromString(id))
                        .orElseThrow(NotFoundException::new));
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book){
        BookDTO createdBook = this.bookService
                .addBook(book);

        URI location = URI.create("/api/books/" + createdBook.getId());

        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String id, @RequestBody BookDTO book){

        BookDTO updatedBook = this.bookService.updateBook(UUID.fromString(id), book);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){

        BookDTO deletedBook = this.bookService.deleteBook(id);

        if(deletedBook == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
