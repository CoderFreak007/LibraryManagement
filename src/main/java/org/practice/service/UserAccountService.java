package org.practice.service;

import org.practice.model.BookCopy;
import org.practice.model.Library;
import org.practice.model.UserAccount;

public interface UserAccountService {

    public void addUser(UserAccount user, Library library);

    public void addBookCopyToUserAccount(int userId, BookCopy bookCopy, Library library);

    public boolean isUserAccountLimitExceeded(int userId, Library library);

    public UserAccount getUserAccountByUserId(int userId, Library library);


}
