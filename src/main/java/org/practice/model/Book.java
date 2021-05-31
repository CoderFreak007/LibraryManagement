package org.practice.model;

import java.util.UUID;

public class Book {

    private String bookId;

    private String title;

    private String author;

    private String publisher;

    public Book(String bookId, String title, String author, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }
}
