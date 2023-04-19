package net.etg.bookregistry.service;

import net.etg.bookregistry.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO saveBook(BookDTO bookDTO);

    List<BookDTO> getBooks();

    BookDTO getSingleBook(Long id);

    BookDTO updateBook(BookDTO bookDTO,Long id);

    String deleteBook(Long id);

}
