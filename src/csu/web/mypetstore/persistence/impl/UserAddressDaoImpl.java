package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.UserAddress;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.UserAddressDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAddressDaoImpl implements UserAddressDao {
    private final static String ADD_USER_ADDRESS = "INSERT INTO USERADDRESS\n" +
            "      (USERID, ADDRID, FIRSTNAME, LASTNAME,  ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY,STATUS)\n" +
            "    VALUES\n" +
            "      (?, ?,?,?, ?,  ?,?, ?, ?,?,?)";

    private final static String GET_USER_ADDRESS ="SELECT\n" +
            "    USERID,\n" +
            "    ADDRID,\n" +
            "    FIRSTNAME,\n" +
            "    LASTNAME,\n" +
            "    ADDR1 AS address1,\n" +
            "    ADDR2 AS address2,\n" +
            "    CITY,\n" +
            "    STATE,\n" +
            "    ZIP,\n" +
            "    COUNTRY,\n" +
            "    STATUS\n" +
            "FROM USERADDRESS\n" +
            "WHERE USERADDRESS.USERID = ?";

    private final String DELETE_USER_ADDRESS ="UPDATE USERADDRESS SET STATUS = ? WHERE USERID = ? AND ADDRID = ?";

    private final String UPDATE_MAIN_ADDRESS ="UPDATE ACCOUNT SET\n" +
            "      FIRSTNAME = ?,\n" +
            "      LASTNAME = ?,\n" +
            "      ADDR1 = ?,\n" +
            "      ADDR2 = ?,\n" +
            "      CITY = ?,\n" +
            "      STATE = ?,\n" +
            "      ZIP = ?,\n" +
            "      COUNTRY = ?\n"+
            "WHERE USERID = ?";

    @Override
    public void addUserAddress(UserAddress userAddress) {
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_ADDRESS);
            preparedStatement.setString(1,userAddress.getUsername());
            List<UserAddress> userAddresses=getUserAddress(userAddress.getUsername());
            preparedStatement.setString(2, String.valueOf(Integer.valueOf(userAddresses.get(userAddresses.size() - 1).getAddressId())+1));
            preparedStatement.setString(3,userAddress.getFirstName());
            preparedStatement.setString(4,userAddress.getLastName());
            preparedStatement.setString(5,userAddress.getAddress1());
            preparedStatement.setString(6,userAddress.getAddress2());
            preparedStatement.setString(7,userAddress.getCity());
            preparedStatement.setString(8,userAddress.getState());
            preparedStatement.setString(9,userAddress.getZip());
            preparedStatement.setString(10,userAddress.getCountry());
            preparedStatement.setString(11,"OK");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserAddress> getUserAddress(String username) {
        List<UserAddress> userAddresses=new ArrayList<UserAddress>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_USER_ADDRESS);
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                UserAddress userAddress=new UserAddress();
                userAddress.setUsername(resultSet.getString("USERID"));
                userAddress.setAddressId(resultSet.getString("ADDRID"));
                userAddress.setFirstName(resultSet.getString("FIRSTNAME"));
                userAddress.setLastName(resultSet.getString("LASTNAME"));
                userAddress.setAddress1(resultSet.getString("address1"));
                userAddress.setAddress2(resultSet.getString("address2"));
                userAddress.setCity(resultSet.getString("CITY"));
                userAddress.setState(resultSet.getString("STATE"));
                userAddress.setZip(resultSet.getString("ZIP"));
                userAddress.setCountry(resultSet.getString("COUNTRY"));
                userAddress.setStatus(resultSet.getString("STATUS"));
                userAddresses.add(userAddress);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAddresses;
    }

    @Override
    public void deleteUserAddress(String username, String addressId) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER_ADDRESS);
            String status="FALSE";
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,addressId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMainAddress(String username, String addressId) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_MAIN_ADDRESS);
            List<UserAddress> userAddress = getUserAddress(username);
            for(UserAddress userAddress1:userAddress) {
                if (userAddress1.getAddressId().equals(addressId)) {
                    preparedStatement.setString(1, userAddress1.getFirstName());
                    preparedStatement.setString(2, userAddress1.getLastName());
                    preparedStatement.setString(3, userAddress1.getAddress1());
                    preparedStatement.setString(4, userAddress1.getAddress2());
                    preparedStatement.setString(5, userAddress1.getCity());
                    preparedStatement.setString(6, userAddress1.getState());
                    preparedStatement.setString(7, userAddress1.getZip());
                    preparedStatement.setString(8, userAddress1.getCountry());
                    preparedStatement.setString(9, username);
                    preparedStatement.executeUpdate();
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   /* public static void main(String[] args) {
        UserAddressDao userAddressDao=new UserAddressDaoImpl();
        *//*List<UserAddress> j2ee = userAddressDao.getUserAddress("j2ee");
        System.out.println(j2ee.get(0).getAddressId());*//*
        userAddressDao.updateMainAddress("j2ee","3");
    }*/
}
