package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountService{
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;
    static {
        LANGUAGE_LIST = List.of("english", "japanese");
        CATEGORY_LIST = List.of("FISH", "DOGS", "REPTILES", "CATS", "BIRDS");
    }
    private AccountDao accountDao;
    public AccountService(){
        this.accountDao = new AccountDaoImpl();
    }

    public List<String> getLanguages() {
        return LANGUAGE_LIST;
    }

    public List<String> getCategories() {
        return CATEGORY_LIST;
    }

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }
}
