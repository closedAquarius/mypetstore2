package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class AccountDaoImpl implements AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD =
            "SELECT\n" +
                    "      SIGNON.USERNAME,\n" +
                    "      ACCOUNT.EMAIL,\n" +
                    "      ACCOUNT.FIRSTNAME,\n" +
                    "      ACCOUNT.LASTNAME,\n" +
                    "      ACCOUNT.STATUS,\n" +
                    "      ACCOUNT.ADDR1 AS address1,\n" +
                    "      ACCOUNT.ADDR2 AS address2,\n" +
                    "      ACCOUNT.CITY,\n" +
                    "      ACCOUNT.STATE,\n" +
                    "      ACCOUNT.ZIP,\n" +
                    "      ACCOUNT.COUNTRY,\n" +
                    "      ACCOUNT.PHONE,\n" +
                    "      PROFILE.LANGPREF AS languagePreference,\n" +
                    "      PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
                    "      PROFILE.MYLISTOPT AS listOption,\n" +
                    "      PROFILE.BANNEROPT AS bannerOption,\n" +
                    "      BANNERDATA.BANNERNAME\n" +
                    "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
                    "    WHERE ACCOUNT.USERID = ?\n" +
                    "      AND SIGNON.PASSWORD = ?\n" +
                    "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
                    "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
                    "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String GET_ACCOUNT_BY_USERNAME =
            "SELECT\n" +
            "      SIGNON.USERNAME,\n" +
            "      ACCOUNT.EMAIL,\n" +
            "      ACCOUNT.FIRSTNAME,\n" +
            "      ACCOUNT.LASTNAME,\n" +
            "      ACCOUNT.STATUS,\n" +
            "      ACCOUNT.ADDR1 AS address1,\n" +
            "      ACCOUNT.ADDR2 AS address2,\n" +
            "      ACCOUNT.CITY,\n" +
            "      ACCOUNT.STATE,\n" +
            "      ACCOUNT.ZIP,\n" +
            "      ACCOUNT.COUNTRY,\n" +
            "      ACCOUNT.PHONE,\n" +
            "      PROFILE.LANGPREF AS languagePreference,\n" +
            "      PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "      PROFILE.MYLISTOPT AS listOption,\n" +
            "      PROFILE.BANNEROPT AS bannerOption,\n" +
            "      BANNERDATA.BANNERNAME\n" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "    WHERE ACCOUNT.USERID = ?\n" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private final static String INSERT_ACCOUNT = "INSERT INTO ACCOUNT\n" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
            "    VALUES\n" +
            "      (?, ?,?,?, ?,  ?,?, ?, ?,?, ?, ?)";
    private final static String INSERT_PROFILE = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)\n" +
            "    VALUES (?, ?, ?)";
    private final static String INSERT_SIGNON = "INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
            "    VALUES (?, ?)";
    private final static String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET\n" +
            "      EMAIL = ?,\n" +
            "      FIRSTNAME = ?,\n" +
            "      LASTNAME = ?,\n" +
            "      STATUS = ?,\n" +
            "      ADDR1 = ?,\n" +
            "      ADDR2 = ?,\n" +
            "      CITY = ?,\n" +
            "      STATE = ?,\n" +
            "      ZIP = ?,\n" +
            "      COUNTRY = ?,\n" +
            "      PHONE = ?\n" +
            "    WHERE USERID = ?";
    private final static String UPDATE_PROFILE = "UPDATE PROFILE SET\n" +
            "      LANGPREF = ?,\n" +
            "      FAVCATEGORY = ?\n" +
            "    WHERE USERID = ?";
    private final static String UPDATE_SIGNON = "UPDATE SIGNON SET PASSWORD = ?\n" +
            "    WHERE USERNAME = ?";
    private final static String UPDATE_PROFILE_FAVCATEGORY = "UPDATE PROFILE SET\n" +
            "      FAVCATEGORY = ?,\n" +
            " MYLISTOPT = ?,\n" +
            "BANNEROPT = ?\n" +
            "    WHERE USERID = ?";
    private final static String GET_ALL_FAVCATEGORY =
            "SELECT FAVCATEGORY FROM PROFILE";

    @Override
    public Account getAccountByUsername(String username) {
        Account accountResult =new Account();
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSetToAccount(accountResult, resultSet);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult =new Account();
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSetToAccount(accountResult, resultSet);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    private void resultSetToAccount(Account accountResult, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            accountResult.setUsername(resultSet.getString("USERNAME"));
            accountResult.setEmail(resultSet.getString("EMAIL"));
            accountResult.setFirstName(resultSet.getString("FIRSTNAME"));
            accountResult.setLastName(resultSet.getString("LASTNAME"));
            accountResult.setStatus(resultSet.getString("STATUS"));
            accountResult.setAddress1(resultSet.getString("address1"));
            accountResult.setAddress2(resultSet.getString("address2"));
            accountResult.setCity(resultSet.getString("city"));
            accountResult.setState(resultSet.getString("STATE"));
            accountResult.setZip(resultSet.getString("ZIP"));
            accountResult.setCountry(resultSet.getString("COUNTRY"));
            accountResult.setPhone(resultSet.getString("PHONE"));
            accountResult.setLanguagePreference(resultSet.getString("languagePreference"));
            accountResult.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
            accountResult.setListOption(resultSet.getInt("listOption")==1?true:false);
            accountResult.setBannerOption(resultSet.getInt("bannerOption")==1?true:false);
            accountResult.setBannerName(resultSet.getString("bannerName"));
        }
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public void updateProfile(Account account) {
            try {
                Connection connection= DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
                preparedStatement.setString(1,account.getLanguagePreference());
                preparedStatement.setString(2,account.getFavouriteCategoryId());
                preparedStatement.setString(3,account.getUsername());
                preparedStatement.executeUpdate();
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfileFavcategory(String favouriteCategoryId, String username) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_FAVCATEGORY);
            preparedStatement.setString(1,favouriteCategoryId);
            preparedStatement.setInt(2,1);
            preparedStatement.setInt(3,1);
            preparedStatement.setString(4,username);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getAllFavcategory() {
        ArrayList<String> AllFavcategory=new ArrayList<String>();
        try {
            Connection connection= DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_FAVCATEGORY);
            while(resultSet.next()){
                AllFavcategory.add(resultSet.getString(1));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AllFavcategory;
    }


    /* public static void main(String[] args) {
        AccountDao accountDao = new AccountDaoImpl();
        Account account = new Account();
        account.setUsername("j2ee");
        account.setPassword("j2ee");
        Account result = accountDao.getAccountByUsernameAndPassword(account);
        System.out.println("sucess");
    }*/
   /*public static void main(String[] args) {
       AccountDao accountDao = new AccountDaoImpl();
       accountDao.updateProfileFavcategory("CATS","yyy");
   }*/
}
