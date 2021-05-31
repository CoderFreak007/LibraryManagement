package org.practice.search;

public interface SearchFactory {

    public BookCopySearch getSearchObject(String searchField);
}
