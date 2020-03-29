package com.poroscareer.bigdataclass.connector.adapters;

import com.poroscareer.bigdataclass.connector.models.Message;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DataAdapter {

    void connect(String url, String username, String password) throws Exception;
    void addOne(String user, String content, Date date) throws SQLException;
    List<Message> findAll() throws SQLException;
    List<Message> findAllByUser(String user) throws SQLException;
    void deleteAllByUser(String user) throws SQLException;
    void disconnect() throws Exception;
}
