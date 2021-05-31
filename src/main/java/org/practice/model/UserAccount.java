package org.practice.model;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends User {

    private int maxAllowedBooks = 5;

    private List<BookCopy> booksBorrowed;

    public UserAccount(int id, String name) {
        super(id, name);
        booksBorrowed = new ArrayList<>();
    }

    public List<BookCopy> getBooksBorrowed() {
        return booksBorrowed;
    }

    public int getMaxAllowedBooks() {
        return maxAllowedBooks;
    }
}
