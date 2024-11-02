package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Journal;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JournalDaoImpl implements JournalDao
{
    private String ADD_JOURNAL = "insert into journal values(?,?,?,?)";
    private String SELECT_JOURNAL = "select * from journal where userId = ?";

    @Override
    public void updateJournal(String userId, String description, String date, String color)
    {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_JOURNAL);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, color);
            preparedStatement.execute();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Journal> getAllJournals(String userId)
    {
        List<Journal> journals = new ArrayList<Journal>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JOURNAL);
            preparedStatement.setString(1,userId);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Journal journal = new Journal();
                journal.setDescription(resultSet.getString("description"));
                journal.setDate(resultSet.getString("date"));
                journal.setColor(resultSet.getString("color"));
                journals.add(journal);
            }
            preparedStatement.execute();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return journals;
    }
}
