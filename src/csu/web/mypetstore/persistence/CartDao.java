package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;

public interface CartDao {
    void addItem(String userId, CartItem cartItem);

    void updateCart(String userId, CartItem cartItem);

    void deleteCart(String userId);

    void deleteItem(String userId, CartItem cartItem);

    Cart getCart(String userId);

}
