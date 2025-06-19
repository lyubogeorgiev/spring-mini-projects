package com.georgievl.bookcatalogapi.bootstrap;

import com.georgievl.bookcatalogapi.entities.Book;
import com.georgievl.bookcatalogapi.reopsitories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        loadBookData();
    }

    private void loadBookData() {
        if (bookRepository.count() == 0) {
            Book book1 = Book.builder()
                    .title("Clean Code")
                    .author("Robert C. Martin")
                    .isbn("9780132350884")
                    .year_published(2008)
                    .build();

            Book book2 = Book.builder()
                    .title("Design Patterns")
                    .author("Erich Gamma")
                    .isbn("9780201633610")
                    .year_published(1994)
                    .build();

            Book book3 = Book.builder()
                    .title("Effective Java")
                    .author("Joshua Bloch")
                    .isbn("9780134685991")
                    .year_published(2017)
                    .build();

            Book book4 = Book.builder()
                    .title("Head First Design Patterns")
                    .author("Eric Freeman")
                    .isbn("9780596007126")
                    .year_published(2004)
                    .build();

            Book book5 = Book.builder()
                    .title("Refactoring")
                    .author("Martin Fowler")
                    .isbn("9780134757599")
                    .year_published(2018)
                    .build();

            Book book6 = Book.builder()
                    .title("Domain-Driven Design")
                    .author("Eric Evans")
                    .isbn("9780321125217")
                    .year_published(2003)
                    .build();

            Book book7 = Book.builder()
                    .title("Test Driven Development")
                    .author("Kent Beck")
                    .isbn("9780321146533")
                    .year_published(2002)
                    .build();

            Book book8 = Book.builder()
                    .title("Working Effectively with Legacy Code")
                    .author("Michael Feathers")
                    .isbn("9780131177055")
                    .year_published(2004)
                    .build();

            Book book9 = Book.builder()
                    .title("Patterns of Enterprise Application Architecture")
                    .author("Martin Fowler")
                    .isbn("9780321127426")
                    .year_published(2002)
                    .build();

            Book book10 = Book.builder()
                    .title("The Pragmatic Programmer")
                    .author("Andrew Hunt")
                    .isbn("9780201616224")
                    .year_published(1999)
                    .build();

            Book book11 = Book.builder()
                    .title("Code Complete")
                    .author("Steve McConnell")
                    .isbn("9780735619670")
                    .year_published(2004)
                    .build();

            Book book12 = Book.builder()
                    .title("The Clean Coder")
                    .author("Robert C. Martin")
                    .isbn("9780137081073")
                    .year_published(2011)
                    .build();

            bookRepository.saveAll(Arrays.asList(
                    book1, book2, book3, book4, book5, book6,
                    book7, book8, book9, book10, book11, book12
            ));

            System.out.println("Loaded " + bookRepository.count() + " books");
        }
    }
}