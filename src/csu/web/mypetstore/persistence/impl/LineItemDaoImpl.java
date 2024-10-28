package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {
    private static final String getLineItemBylineItemIdString =
            "SELECT lineItemID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICEFROM LINEITEM WHERE lineItemID = ?";
    private static final String insertLineItemString =
            "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId)
    {
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatementStatement = connection.prepareStatement(getLineItemBylineItemIdString);
            preparedStatementStatement.setString(1,orderId +"");
            ResultSet resultSet = preparedStatementStatement.executeQuery();
            while(resultSet.next())
            {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatementStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatementStatement = connection.prepareStatement(insertLineItemString);

            preparedStatementStatement.setString(1,lineItem.getOrderId()+"");
            preparedStatementStatement.setString(2,lineItem.getLineNumber() + "");
            preparedStatementStatement.setString(3,lineItem.getItemId());
            preparedStatementStatement.setString(4,lineItem.getQuantity() + "");
            preparedStatementStatement.setString(5,lineItem.getUnitPrice()+"");

            preparedStatementStatement.execute();
            DBUtil.closePreparedStatement(preparedStatementStatement);
            DBUtil.closeConnection(connection);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
