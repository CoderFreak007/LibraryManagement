package org.practice.search;

import org.practice.model.BookCopy;
import org.practice.model.Library;

import java.util.List;

public interface BookCopySearch {

    public List<BookCopy> search(Library library, String value);
}
