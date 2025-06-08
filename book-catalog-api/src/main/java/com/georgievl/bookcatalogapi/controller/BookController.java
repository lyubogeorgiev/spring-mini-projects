package com.georgievl.bookcatalogapi.controller;

import com.georgievl.bookcatalogapi.model.Book;
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
    public ResponseEntity<Map<UUID, Book>> getBooks(){
        return this.bookService.getAllBooks() != null ?
                ResponseEntity.ok(this.bookService.getAllBooks()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        Book book = this.bookService.getBookById(UUID.fromString(id));

        if(book == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book createdBook = this.bookService
                .addBook(book);

        URI location = URI.create("/api/books/" + createdBook.getId());

        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book){

        Book updatedBook = this.bookService.updateBook(UUID.fromString(id), book);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){

        Book deletedBook = this.bookService.deleteBook(id);

        if(deletedBook == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
