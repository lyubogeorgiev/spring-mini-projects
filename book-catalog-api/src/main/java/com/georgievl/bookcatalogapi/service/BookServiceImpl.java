package com.georgievl.bookcatalogapi.service;

import com.georgievl.bookcatalogapi.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{
    List<Book> sampleBooks = List.of(
            new Book(null, "1984", "George Orwell", "9780451524935", 1949),
            new Book(null, "To Kill a Mockingbird", "Harper Lee", "9780061120084", 1960),
            new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925),
            new Book(null, "Pride and Prejudice", "Jane Austen", "9780141439518", 1813),
            new Book(null, "The Catcher in the Rye", "J.D. Salinger", "9780316769488", 1951),
            new Book(null, "The Hobbit", "J.R.R. Tolkien", "9780345339683", 1937),
            new Book(null, "Fahrenheit 451", "Ray Bradbury", "9781451673319", 1953),
            new Book(null, "Brave New World", "Aldous Huxley", "9780060850524", 1932),
            new Book(null, "Jane Eyre", "Charlotte Brontë", "9780142437209", 1847),
            new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", "9780618640157", 1954),
            new Book(null, "The Chronicles of Narnia", "C.S. Lewis", "9780066238500", 1956),
            new Book(null, "Moby Dick", "Herman Melville", "9781503280786", 1851)
    );

    Map<UUID, Book> books = new HashMap<>();

    @PostConstruct
    public void initSampleBooks(){
        sampleBooks.forEach(this::addBook);
    }

    @Override
    public Book addBook(Book book){
        book.setId(UUID.randomUUID().toString());
        books.put(UUID.fromString(book.getId()), book);

        return book;
    }

    @Override
    public Book updateBook(UUID id, Book book) {

        Book existingBook = books.get(id);
        if(existingBook != null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setYear(book.getYear());
            return existingBook;
        }

        return null;
    }

    @Override
    public Book deleteBook(UUID id) {

        return books.remove(id);
    }

    @Override
    public Map<UUID, Book> getAllBooks() {
        return this.books;
    }

    @Override
    public Book getBookById(UUID id) {
        return this.books.get(id);
    }
}
