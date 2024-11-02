package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Account;

import java.util.ArrayList;

public interface AccountDao {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);

    void updateProfileFavcategory(String favouriteCategoryId, String username);

    ArrayList<String> getAllFavcategory();
}
