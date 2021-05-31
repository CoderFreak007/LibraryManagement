package org.practice.service;

import org.practice.model.BookCopy;
import org.practice.model.Rack;

public interface RackService {

    public boolean isCopyAvailableOnRack(Rack rack, String bookCopyId);

    public boolean isBookCopyAvailableOnRack(Rack rack, String bookId);

    public int placeBookCopyOnRack(Rack rack, BookCopy bookCopy);

    public int removeBookCopyFromRack(Rack rack, String bookCopyId);

    public BookCopy getBookCopyByBookId(Rack rack, String bookId);

    public BookCopy getBookCopyByBookCopyId(Rack rack, String bookCopyId);
}
