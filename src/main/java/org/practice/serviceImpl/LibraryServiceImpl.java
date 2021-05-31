package org.practice.serviceImpl;

import org.practice.model.*;
import org.practice.search.BookCopySearch;
import org.practice.search.SearchFactory;
import org.practice.searchImpl.SearchFactoryImpl;
import org.practice.service.LibraryService;
import org.practice.service.RackService;
import org.practice.service.UserAccountService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private RackService rackService = new RackServiceImpl();

    private UserAccountService userAccountService = new UserAccountServiceImpl();

    private SearchFactory searchFactoryImpl = new SearchFactoryImpl();

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");


    @Override
    public Library createLibrary(String libraryName, int noOfRacks){
        Library library = new Library(libraryName, noOfRacks);
        System.out.println("library created with name "+library);
        return library;
    }

    @Override
    public void addBook(String bookId, String bookName, String author,
                        String publisher, String bookCopyIds, Library library) throws Exception {

        Book newBook = new Book(bookId, bookName, author, publisher);
        placeBookInLibrary(library, newBook);
        List<BookCopy> bookCopies = new ArrayList<>();
        for(String bookCopyId : bookCopyIds.split(","))
            bookCopies.add(new BookCopy(newBook, bookCopyId));
        List<Integer> rackNumbers = distributeCopiesAcrossRacks(bookCopies, library);
        System.out.println("Book added on racks "+rackNumbers.toString());

    }

    @Override
    public void removeBookCopy(String bookCopyId, Library library) throws Exception {
        Rack[] racks = library.getRacksInLibrary();
        for(Rack rack : racks){
            if(rackService.isCopyAvailableOnRack(rack, bookCopyId)) {
                rackService.removeBookCopyFromRack(rack, bookCopyId);
                System.out.println(bookCopyId + "removed from "+rack.getRackNumber());
                return;
            }
        }
        throw new Exception(bookCopyId +" does not exist in library");


    }

    @Override
    public void borrowBook(String bookId, int userid, Date duedate, Library library) throws Exception {

        if(userAccountService.isUserAccountLimitExceeded(userid, library))
            throw new Exception("Over Limit");

        Rack[] racks = library.getRacksInLibrary();
        BookCopy bookCopy = null;

        if(!bookAvailableInLibrary(library, bookId))
            throw new Exception("Invalid Book Id");

        for(Rack rack : racks){
            bookCopy = rackService.getBookCopyByBookId(rack, bookId);
            if(bookCopy != null){
                rackService.removeBookCopyFromRack(rack, bookCopy.getBookCopyId());
                break;
            }
        }
        if(bookCopy == null)
            throw new Exception("No book Copy Available");

        userAccountService.addBookCopyToUserAccount(userid, bookCopy, library);

        System.out.println("book copy "+ bookCopy.getBookCopyId()+" borrowed by " +
                "user "+userid + "for due date"+dateFormat.format(duedate));


    }

    @Override
    public void borrowBookCopy(String bookCopyId, int userid, Date duedate, Library library) throws Exception {
        Rack[] racks = library.getRacksInLibrary();
        BookCopy bookCopy = null;

        for(Rack rack : racks){
            if(rackService.isCopyAvailableOnRack(rack, bookCopyId)) {
                bookCopy = rackService.getBookCopyByBookCopyId(rack, bookCopyId);
                rackService.removeBookCopyFromRack(rack, bookCopyId);
                break;
            }
        }
        if(bookCopy == null)
            throw new Exception("No book Copy Available");

        userAccountService.addBookCopyToUserAccount(userid, bookCopy, library);

        System.out.println("book copy "+ bookCopy.getBookCopyId()+" borrowed by " +
                "user "+userid + "for due date"+dateFormat.format(duedate));


    }

    @Override
    public void createUserAccount(int userId, String userName , Library library){
        UserAccount userAccount = new UserAccount(userId, userName);
        userAccountService.addUser(userAccount, library);

    }

    @Override
    public void printBorrowedBooks(int userId, Library library){
       UserAccount userAccount = userAccountService.getUserAccountByUserId(userId, library);
       List<BookCopy> booksCopies = userAccount.getBooksBorrowed();

       System.out.print("books borrowed by "+userId+"are ");
       for(BookCopy bookCopy : booksCopies){
           System.out.print(bookCopy.getBookCopyId()+",");
       }
    }

    @Override
    public void searchBook(String attribute , String attributeValue, Library library){
        BookCopySearch bookCopySearch = searchFactoryImpl.getSearchObject(attribute);
        if(bookCopySearch == null)
            throw new IllegalArgumentException("Invalid attribute");
        List<BookCopy> booksCopies = bookCopySearch.search(library, attributeValue);
        System.out.print("search results are->");
        for(BookCopy bookCopy : booksCopies){
            System.out.print(bookCopy.getBookCopyId()+",");
        }
    }

    private List<Integer> distributeCopiesAcrossRacks(List<BookCopy> bookCopies, Library library) throws Exception {
        List<Integer> rackNoForBookCopies = new ArrayList<>();
        for(BookCopy bookCopy :  bookCopies)
            rackNoForBookCopies.add(placeAbookCopyOnRack(bookCopy, library));
        return rackNoForBookCopies;

    }

    private int placeAbookCopyOnRack(BookCopy bookCopy, Library library) throws Exception {
        Rack[] racks = library.getRacksInLibrary();
            for(Rack rack : racks){
                if(!rackService.isBookCopyAvailableOnRack(rack, bookCopy.getBook().getBookId()))
                  return rackService.placeBookCopyOnRack(rack, bookCopy);
            }
            throw new Exception("No racks Avaiable");

    }

    private void placeBookInLibrary(Library library, Book book){
        library.getBooksMapByBookId().put(book.getBookId(), book);
    }

    private boolean bookAvailableInLibrary(Library library, String bookId){
        return library.getBooksMapByBookId().containsKey(bookId);
    }







}
