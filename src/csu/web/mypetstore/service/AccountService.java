package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AccountService{
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;
    private static final int BIRDS=0;
    private static final int CATS=1;
    private static final int DOGS=2;
    private static final int FISH=3;
    private static final int REPTILES=4;

    static
    {
        LANGUAGE_LIST = Arrays.asList("english", "japanese");
        CATEGORY_LIST = Arrays.asList("FISH", "DOGS", "REPTILES", "CATS", "BIRDS");
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

    public void updateProfileFavcategory(String favouriteCategoryId, Account account){
        accountDao.updateProfileFavcategory(favouriteCategoryId,account.getUsername());
    }

    public String getAllFavcategory(){
        ArrayList<String> list=accountDao.getAllFavcategory();
        String[] categories={"BIRDS","CATS","DOGS","FISH","REPTILES"};
        int[] count={0,0,0,0,0};
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("BIRDS")) count[0]++;
            if(list.get(i).equals("CATS")) count[1]++;
            if(list.get(i).equals("DOGS")) count[2]++;
            if(list.get(i).equals("FISH")) count[3]++;
            if(list.get(i).equals("REPTILES")) count[4]++;
        }
        int j=0;
        for(int i=0;i<categories.length-1;i++){
            if(count[i]<count[i+1]){
                j=i+1;
            }
        }
        return categories[j];
    }
}
