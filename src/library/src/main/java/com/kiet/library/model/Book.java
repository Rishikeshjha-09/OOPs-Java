
package com.kiet.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "book_id")
    @Positive(message = "Book ID must be greater than zero")
    private int bookId;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Column(nullable = false, length = 150)
    private String author;

    @NotBlank(message = "Category cannot be blank")
    @Column(nullable = false, length = 100)
    private String category;

    @Min(value = 1, message = "Publication year must be positive")
    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private boolean available;

    public Book() {
    }

    public Book(int bookId, String title, String author,
                String category, int publicationYear,
                boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publicationYear = publicationYear;
        this.available = available;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
