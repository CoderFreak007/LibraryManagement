package org.practice.service;

import org.practice.model.Library;

import java.util.Date;

public interface LibraryService {

    public Library createLibrary(String libraryName, int noOfRacks);

    public void addBook(String bookId, String bookName, String author,
                        String publisher, String bookCopyIds, Library library) throws Exception;

    public void removeBookCopy(String bookCopyId, Library library) throws Exception;

    public void borrowBook(String bookId, int userid, Date duedate, Library library) throws Exception;

    public void borrowBookCopy(String bookCopyId, int userid, Date duedate, Library library) throws Exception;

    public void createUserAccount(int userId, String userName , Library library);

    public void printBorrowedBooks(int userId, Library library);

    public void searchBook(String attribute , String attributeValue, Library library);
}
