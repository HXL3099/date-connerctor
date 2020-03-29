package com.poroscareer.bigdataclass.connector.adapters.impl;

import com.poroscareer.bigdataclass.connector.adapters.DataAdapter;
import com.poroscareer.bigdataclass.connector.models.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PostgresAdapterTest {

    private DataAdapter dataAdapter;

    @Before
    public void setUp() throws Exception {

        String url = "jdbc:postgresql://postgres-instance:5432/postgres";
        String username = "admin";
        String password = "password";
        String tableName = "CHAT_MESSAGES";

        this.dataAdapter = new PostgresAdapter(tableName);
        this.dataAdapter.connect(url, username, password);
    }

    private boolean checkDataExistence(String user) throws SQLException {

        List<Message> messageList = dataAdapter.findAllByUser(user);

        return messageList.size() != 0;
    }

    @Test
    public void testAddOneHappyPath() throws SQLException {
        // 1. Prepare data
        String user = "bill";
        String content = "test message";
        java.util.Date now = new java.util.Date();
        Date date = new java.sql.Date(now.getTime());

        // 2. Mock

        // 3. Call method
        dataAdapter.addOne(user, content, date);

        // 4. Verify
        // Expected: True
        // Actual: Existence in Database
        Assert.assertTrue(checkDataExistence(user));
    }

    @Test
    public void testAddOneUserNull() {
        // 1. Prepare data
        String user = null;
        String content = "test message";
        java.util.Date now = new java.util.Date();
        Date date = new java.sql.Date(now.getTime());

        // 2. Mock

        try {
            // 3. Call method
            dataAdapter.addOne(user, content, date);
            // 4. Verify
            Assert.fail();
        } catch (SQLException e) {
            // 4. Verify
        }
    }

    @Test
    public void testAddOneContentNull() {
        // 1. Prepare data
        String user = "bill";
        String content = null;
        java.util.Date now = new java.util.Date();
        Date date = new java.sql.Date(now.getTime());

        // 2. Mock

        try {
            // 3. Call method
            dataAdapter.addOne(user, content, date);
            // 4. Verify
            Assert.fail();
        } catch (SQLException e) {
            // 4. Verify
        }
    }

    @Test
    public void testAddOneDateNull() {
        // 1. Prepare data
        String user = "bill";
        String content = "test message";
        Date date = null;

        // 2. Mock

        try {
            // 3. Call method
            dataAdapter.addOne(user, content, date);
            // 4. Verify
            Assert.fail();
        } catch (SQLException e) {
            // 4. Verify
        }
    }

    @After
    public void tearDown() throws Exception {

        this.dataAdapter.disconnect();
    }
}