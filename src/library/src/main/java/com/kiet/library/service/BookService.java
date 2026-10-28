
package com.kiet.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kiet.library.model.Book;
import com.kiet.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int bookId, Book updatedBook) {
        Book book = getBookById(bookId);

        if (book == null) {
            return null;
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setAvailable(updatedBook.isAvailable());

        return bookRepository.save(book);
    }

    public boolean deleteBook(int bookId) {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }

    public List<Book> searchByCategory(String category) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getCategory() != null
                        && book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Book> findPublishedFromYear(int fromYear) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getPublicationYear() >= fromYear)
                .toList();
    }
}
