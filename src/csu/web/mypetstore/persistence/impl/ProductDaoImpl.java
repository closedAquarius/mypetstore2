package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String GET_PRODUCT_LIST_BY_CATEGORY =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String GET_PRODUCT =
            "SELECT PRODUCTID,NAME, DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String SEARCH_PRODUCT_LIST =
            "select PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    private static final String GET_CATEGORY_BY_PRODUCT =
            "SELECT CATEGORY FROM PRODUCT WHERE PRODUCTID =?";

    @Override
    public String getCategoryByProduct(String productId) {
        String categoryId = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_PRODUCT);
            statement.setString(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoryId = resultSet.getString("CATEGORY");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryId;
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            statement.setString(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Product product=new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT);
            statement.setString(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                product=new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            statement.setString(1, keywords);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Product product=new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
