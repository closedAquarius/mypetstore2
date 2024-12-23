package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.UserAddress;

import java.util.List;

public interface UserAddressDao {
    public void addUserAddress(UserAddress userAddress);

    public List<UserAddress> getUserAddress(String username);

    public void deleteUserAddress(String username, String addressId);

    public void updateMainAddress(String username, String addressId);
}
