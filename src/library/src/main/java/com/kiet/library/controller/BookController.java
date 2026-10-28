
package com.kiet.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kiet.library.model.Book;
import com.kiet.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found with ID: " + bookId
            );
        }

        return book;
    }

    @PostMapping
    @org.springframework.web.bind.annotation.ResponseStatus(
            HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(
            @PathVariable int bookId,
            @Valid @RequestBody Book updatedBook) {

        Book book = bookService.updateBook(bookId, updatedBook);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found with ID: " + bookId
            );
        }

        return book;
    }

    @DeleteMapping("/{bookId}")
    public org.springframework.http.ResponseEntity<Void> deleteBook(
            @PathVariable int bookId) {

        boolean deleted = bookService.deleteBook(bookId);

        if (!deleted) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found with ID: " + bookId
            );
        }

        return org.springframework.http.ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Book> searchByCategory(
            @RequestParam String category) {
        return bookService.searchByCategory(category);
    }

    @GetMapping("/published")
    public List<Book> findPublishedFromYear(
            @RequestParam int fromYear) {
        return bookService.findPublishedFromYear(fromYear);
    }
}
