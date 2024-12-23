package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.UserAddress;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.UserAddressDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.persistence.impl.UserAddressDaoImpl;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private UserAddressDao userAddressDao;
    public AccountService(){
        this.accountDao = new AccountDaoImpl();
        this.userAddressDao = new UserAddressDaoImpl();
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

    public void updateItemJournal(Account account,String itemId){
        if(account!=null){
            JournalDao journalDao =new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseItemString = "User "+ account.getUsername() + " browsed the item: "
                        + "<a href=\"itemForm?itemId=" + itemId + "\">" + itemId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseItemString, currentDate, "#70AD47");
        }
    }

    public void updateCategoryJournal(Account account,String categoryId){
        if(account!=null){
            JournalDao journalDao =new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseCategoryString = "User "+ account.getUsername() + " browsed the product category: "
                    + "<a href=\"categoryForm?categoryId=" + categoryId + "\">" + categoryId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseCategoryString, currentDate, "#70AD47");
        }
    }

    public void updateProductJournal(Account account,String productId){
        if (account != null)
        {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseItemString = "User "+ account.getUsername() + " browsed the product: "
                    + "<a href=\"productForm?productId=" + productId + "\">" + productId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseItemString, currentDate, "#70AD47");
        }
    }

    public void updateSignOff(Account account){
        if (account != null)
        {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String loginOutString = "User "+ account.getUsername() + " logged out.";
            journalDao.updateJournal(account.getUsername(), loginOutString, currentDate, "#4472C4");
        }
    }

    public List<UserAddress> getAllAddressByUsername(String username){
        List<UserAddress> userAddress = userAddressDao.getUserAddress(username);
        return userAddress;
    }

    public UserAddress getUserAddressByAddressId(String username,String addressId) {
        List<UserAddress> userOKAddressByUsername = getUserOKAddressByUsername(username);
        for (UserAddress userAddress : userOKAddressByUsername) {
            if (userAddress.getAddressId().equals(addressId)) {
                return userAddress;
            }
        }
        return null;
    }
    public List<UserAddress> getUserOKAddressByUsername(String username){
        List<UserAddress> userAddress = getAllAddressByUsername(username);
        List<UserAddress> userAddressOK = new ArrayList<>();
        for (UserAddress userAddress1 : userAddress) {
            if (userAddress1.getStatus()!=null&&userAddress1.getStatus().equals("OK")) {
                userAddressOK.add(userAddress1);
            }
        }
        return userAddressOK;
    }


    public void deleteUserAddress(String username, String addressId){
        userAddressDao.deleteUserAddress(username, addressId);
    }

    public void insertUserAddress(UserAddress userAddress){
        userAddressDao.addUserAddress(userAddress);
    }

    public void upadteUserMainAddress(String username,String addressId){
        userAddressDao.updateMainAddress(username,addressId);
    }
}
