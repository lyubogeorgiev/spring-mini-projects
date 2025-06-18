package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.model.BookDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService{
    List<BookDTO> sampleBooks = List.of(
            new BookDTO(null, "1984", "George Orwell", "9780451524935", 1949),
            new BookDTO(null, "To Kill a Mockingbird", "Harper Lee", "9780061120084", 1960),
            new BookDTO(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925),
            new BookDTO(null, "Pride and Prejudice", "Jane Austen", "9780141439518", 1813),
            new BookDTO(null, "The Catcher in the Rye", "J.D. Salinger", "9780316769488", 1951),
            new BookDTO(null, "The Hobbit", "J.R.R. Tolkien", "9780345339683", 1937),
            new BookDTO(null, "Fahrenheit 451", "Ray Bradbury", "9781451673319", 1953),
            new BookDTO(null, "Brave New World", "Aldous Huxley", "9780060850524", 1932),
            new BookDTO(null, "Jane Eyre", "Charlotte Brontë", "9780142437209", 1847),
            new BookDTO(null, "The Lord of the Rings", "J.R.R. Tolkien", "9780618640157", 1954),
            new BookDTO(null, "The Chronicles of Narnia", "C.S. Lewis", "9780066238500", 1956),
            new BookDTO(null, "Moby Dick", "Herman Melville", "9781503280786", 1851)
    );

    Map<UUID, BookDTO> books = new HashMap<>();

    @PostConstruct
    public void initSampleBooks(){
        sampleBooks.forEach(this::addBook);
    }

    @Override
    public BookDTO addBook(BookDTO book){
        book.setId(UUID.randomUUID().toString());
        books.put(UUID.fromString(book.getId()), book);

        return book;
    }

    @Override
    public BookDTO updateBook(UUID id, BookDTO book) {

        BookDTO existingBook = books.get(id);
        if(existingBook != null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setYear_published(book.getYear_published());
            return existingBook;
        }

        return null;
    }

    @Override
    public BookDTO deleteBook(UUID id) {

        return books.remove(id);
    }

    @Override
    public Map<UUID, BookDTO> getAllBooks() {
        return this.books;
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.of(this.books.get(id));
    }
}
