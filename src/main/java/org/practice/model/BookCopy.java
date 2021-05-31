package org.practice.model;

import java.util.UUID;

public class BookCopy {

    private String bookCopyId;

    private Book book;

    public BookCopy(Book book, String bookCopyId) {
       this.book = book;
       this.bookCopyId = bookCopyId;

    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public Book getBook() {
        return book;
    }
}
