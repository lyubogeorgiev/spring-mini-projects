package com.georgievl.bookcatalogapi.mappers;

import com.georgievl.bookcatalogapi.entities.Book;
import com.georgievl.bookcatalogapi.model.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book BookDTOToBook(BookDTO bookDTO);

    BookDTO BookToBookDTO(Book book);
}
