package org.practice.searchImpl;

import org.practice.model.BookCopy;
import org.practice.model.Library;
import org.practice.model.Rack;
import org.practice.search.BookCopySearch;

import java.util.ArrayList;
import java.util.List;

public class TitleSearch implements BookCopySearch {
    @Override
    public List<BookCopy> search(Library library, String value) {
        Rack[] racks = library.getRacksInLibrary();
        List<BookCopy> searchResult = new ArrayList<>();
        for(Rack rack : racks){
            for(BookCopy bookCopy : rack.getBookCopiesMapByCopyId().values()){
                if(bookCopy.getBook().getTitle().equals(value))
                    searchResult.add(bookCopy);
            }
        }
        return searchResult;
    }
}
