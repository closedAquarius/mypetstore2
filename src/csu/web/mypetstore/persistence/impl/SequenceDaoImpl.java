package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    private static final String getSequenceString =
            "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String updateSequenceString =
            "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatementStatement = connection.prepareStatement(getSequenceString);
            preparedStatementStatement.setString(1,sequence.getName());
            ResultSet resultSet = preparedStatementStatement.executeQuery();
            if(resultSet.next())
            {
                sequence = new Sequence();
                sequence.setName(resultSet.getString(1));
                sequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatementStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return sequence;
    }

    @Override
    public void updateSequence(Sequence sequence)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatementStatement = connection.prepareStatement(updateSequenceString);
            preparedStatementStatement.setString(1,sequence.getNextId()+"");
            preparedStatementStatement.setString(2,sequence.getName());
            preparedStatementStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatementStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
