package org.practice.serviceImpl;

import org.practice.model.BookCopy;
import org.practice.model.Library;
import org.practice.model.UserAccount;
import org.practice.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {

    @Override
    public void addUser(UserAccount user, Library library){
        library.getUserAccounts().add(user);
    }

    @Override
    public void addBookCopyToUserAccount(int userId, BookCopy bookCopy, Library library){
        UserAccount userAccount = getUserAccountByUserId(userId, library);
        userAccount.getBooksBorrowed().add(bookCopy);
    }

    @Override
    public boolean isUserAccountLimitExceeded(int userId, Library library){
        UserAccount userAccount = getUserAccountByUserId(userId, library);
        return userAccount.getBooksBorrowed().size() == userAccount.getMaxAllowedBooks();
    }


    public UserAccount getUserAccountByUserId(int userId, Library library){
        for(UserAccount userAccunt : library.getUserAccounts()){
            if(userAccunt.getId() == userId){
                return userAccunt;
            }
        }
        throw new IllegalArgumentException("user does not exist");
    }


}
