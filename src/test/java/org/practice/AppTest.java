package org.practice;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.practice.model.Library;
import org.practice.service.LibraryService;
import org.practice.serviceImpl.LibraryServiceImpl;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */



    public static Library library = null;

    public static LibraryService libraryService = null;

    @BeforeClass
    public static void creatLibrary(){
        libraryService = new LibraryServiceImpl();
        library = libraryService.createLibrary("librariry1", 2);
        libraryService.createUserAccount(1, "Krishna", library);
        libraryService.createUserAccount(2, "Gupta", library);
    }

    @Test
    public void addBookToLibrary() throws Exception {

        libraryService.addBook("book1","BookPart1","author1",
                "publisher1","book1copy1, book1copy2", library);
        assertTrue( true );
    }
}
