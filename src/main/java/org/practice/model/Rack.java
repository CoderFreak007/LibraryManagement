package org.practice.model;

import java.util.HashMap;
import java.util.Map;

public class Rack {

    private static int rackNumberForassigning = 1;

    private int rackNumber = 1;

    private Map<String, BookCopy> bookCopiesMapByCopyId;

    public Rack() {
        rackNumber = rackNumberForassigning;
        bookCopiesMapByCopyId = new HashMap<>();
        rackNumberForassigning++;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public Map<String, BookCopy> getBookCopiesMapByCopyId() {
        return bookCopiesMapByCopyId;
    }


}
