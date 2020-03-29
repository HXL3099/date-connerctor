package com.poroscareer.bigdataclass.connector.adapters.impl;

import com.poroscareer.bigdataclass.connector.adapters.DataAdapter;
import com.poroscareer.bigdataclass.connector.models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAdapter implements DataAdapter {

    private Connection connection;
    private String tableName;

    public PostgresAdapter(String tableName) {

        this.tableName = tableName;
    }

    @Override
    public void connect(String url, String username, String password) throws SQLException {

        this.connection = DriverManager.getConnection(url, username, password);

        if (connection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    @Override
    public void addOne(String user, String content, Date date) throws SQLException {

        String sql = "INSERT INTO " + tableName + " (PARTICIPANT, MSG_CONTENT, MSG_DATE) " +
                     "VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, content);
        preparedStatement.setDate(3, date);

        preparedStatement.execute();
        preparedStatement.close();

        System.out.println("Data insert success!");
    }

    @Override
    public List<Message> findAll() throws SQLException {

        List<Message> messages = new ArrayList<>();

        String sql = "SELECT * FROM " + tableName + ";";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long currId = resultSet.getLong("id");
            String currUser = resultSet.getString("participant");
            String currContent = resultSet.getString("msg_content");
            Date currDate = resultSet.getDate("msg_date");
            Message message = new Message(currId, currUser, currContent, currDate);
            messages.add(message);
        }

        resultSet.close();
        statement.close();

        return messages;
    }

    @Override
    public List<Message> findAllByUser(String user) throws SQLException {

        List<Message> messages = new ArrayList<>();

        String sql = "SELECT * FROM " + tableName +
                    " WHERE PARTICIPANT = '" + user + "';";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int currId = resultSet.getInt("id");
            String currUser = resultSet.getString("participant");
            String currContent = resultSet.getString("msg_content");
            Date currDate = resultSet.getDate("msg_date");
            Message message = new Message(currId, currUser, currContent, currDate);
            messages.add(message);
        }

        resultSet.close();
        statement.close();

        return messages;
    }

    @Override
    public void deleteAllByUser(String user) throws SQLException {

        String sql = "DELETE FROM " + tableName +
                    " WHERE PARTICIPANT = '" + user + "';";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        statement.close();
    }

    @Override
    public void disconnect() throws SQLException {

        connection.close();
    }
}
