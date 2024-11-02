package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
 import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartDaoImpl implements CartDao
{
    private String ADD_ITEM="insert into cart values(?,?,?)";
    private String UPDATE_CART="update cart set quantity=? where itemid=? and userid=?";
    private String DELETE_CART="delete from cart where  userid=?";
    private String DELETE_ITEM="delete from cart where itemid=? and userid=?";
    private String SELECT_CART="select * from cart where userid=?";

    @Override
    public void addItem(String userId, CartItem cartItem) 
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_ITEM);
            ps.setString(1, cartItem.getItem().getItemId());
            ps.setString(2, userId);
            ps.setInt(3, cartItem.getQuantity());
            ps.execute();
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(String userId, CartItem cartItem)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CART);
            ps.setInt(1, cartItem.getQuantity());
            ps.setString(3,userId);
            ps.setString(2,cartItem.getItem().getItemId());
            //System.out.println("begin update");
            //System.out.println(cartItem.getQuantity());
            //System.out.println(userId);
            //System.out.println(cartItem.getItem().getItemId());
            ps.execute();
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(String userId) 
    {
        try 
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CART);
            ps.setString(1,userId);
            ps.execute();
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(String userId, CartItem cartItem) 
    {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1,cartItem.getItem().getItemId());
            ps.setString(2,userId);
            ps.execute();
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Cart getCart(String userId)
    {
        Cart cart=new Cart();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_CART);
            ps.setString(1,userId);
            ResultSet resultSet=ps.executeQuery();
            CatalogService catalogService=new CatalogService();
            while(resultSet.next()){
                Item item=catalogService.getItem(resultSet.getString("itemid"));
                boolean isInStock = catalogService.isItemInStock(item.getItemId());
                item.setQuantity(resultSet.getInt("quantity")-1);
                cart.addItem(item,isInStock);
            }
            ps.execute();
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cart;
    }

    /*public static void main(String[] args) {
        CatalogService catalogService=new CatalogService();
        Item item=catalogService.getItem("EST-1");
        CartItem cartItem=new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(1);
        CartDao cartDao=new CartDaoImpl();
        //cartDao.addItem("1",cartItem);
        //cartDao.deleteCart("1");
        cartItem.setQuantity(2);
        cartDao.updateCart("1",cartItem);
    }*/
}
