package org.practice.searchImpl;

import org.practice.search.BookCopySearch;
import org.practice.search.SearchFactory;

public class SearchFactoryImpl implements SearchFactory {

    @Override
    public BookCopySearch getSearchObject(String searchField){

        switch (searchField.toLowerCase()){
            case "bookid":
                return new BookSearch();
            case "author":
                return new AuthorSearch();
            case "title":
                return new TitleSearch();
            case "publisher":
                return new PublisherSearch();
        }
        return null;

    }

}
