package net.etg.bookregistry.service.implementation;

import net.etg.bookregistry.dto.BookDTO;
import net.etg.bookregistry.entity.Book;
import net.etg.bookregistry.exception.NoSuchBookException;
import net.etg.bookregistry.repository.BookRepository;
import net.etg.bookregistry.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO,Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook,BookDTO.class);
    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOList = books.stream()
                .map( book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return bookDTOList;
    }

    @Override
    public BookDTO getSingleBook(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book doesn't exists"));
        return modelMapper.map(existingBook, BookDTO.class);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO,Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book doesn't exists"));
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setName(bookDTO.getName());
        existingBook.setPrice(bookDTO.getPrice());
        Book updatedBook = bookRepository.save(existingBook);
        BookDTO updatedBookDTO = modelMapper.map(updatedBook,BookDTO.class);
        return updatedBookDTO;
    }

    @Override
    public String deleteBook(Long id) {

        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book doesn't exists"));
        bookRepository.delete(existingBook);
        return "Book with id: "+ id + " deleted";
    }
}
