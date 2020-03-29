package com.poroscareer.bigdataclass.connector.models;

import java.sql.Date;

public class Message {

    private long id;
    private String user;
    private String content;
    private Date date;

    public Message(long id, String user, String content, Date date) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
