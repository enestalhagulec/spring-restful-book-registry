package net.etg.bookregistry.controller;

import jakarta.validation.Valid;
import net.etg.bookregistry.dto.BookDTO;
import net.etg.bookregistry.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id){
        BookDTO existingBookDTO = bookService.getSingleBook(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingBookDTO);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> existingBookDTOs = bookService.getBooks();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingBookDTOs);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO){
        BookDTO savedBookDTO = bookService.saveBook(bookDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBookDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
                                              @Valid @RequestBody BookDTO bookDTO){
        BookDTO changedBookDTO = bookService.updateBook(bookDTO,id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(changedBookDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

}
