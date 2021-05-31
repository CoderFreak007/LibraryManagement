package org.practice.serviceImpl;

import org.practice.model.BookCopy;
import org.practice.model.Rack;
import org.practice.service.RackService;

import java.util.Map;

public class RackServiceImpl implements RackService {

    @Override
    public boolean isBookCopyAvailableOnRack(Rack rack, String bookId){

        BookCopy bookCopy = getBookCopyByBookId(rack, bookId);

        return bookCopy != null;
    }

    @Override
    public boolean isCopyAvailableOnRack(Rack rack, String bookCopyId){
        return rack.getBookCopiesMapByCopyId().containsKey(bookCopyId);
    }

    @Override
    public int placeBookCopyOnRack(Rack rack, BookCopy bookCopy){
        rack.getBookCopiesMapByCopyId().put(bookCopy.getBookCopyId(), bookCopy);
        return rack.getRackNumber();
    }

    @Override
    public int removeBookCopyFromRack(Rack rack, String bookCopyId){
        rack.getBookCopiesMapByCopyId().remove(bookCopyId);
        return rack.getRackNumber();
    }

    @Override
    public BookCopy getBookCopyByBookId(Rack rack, String bookId){
          for(BookCopy bookCopy : rack.getBookCopiesMapByCopyId().values()){
              if(bookCopy.getBook().getBookId().equals(bookId))
                  return bookCopy;
          }
          return null;
    }

    @Override
    public BookCopy getBookCopyByBookCopyId(Rack rack, String bookCopyId){
         return rack.getBookCopiesMapByCopyId().get(bookCopyId);

    }


}
