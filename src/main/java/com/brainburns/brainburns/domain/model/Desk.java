package com.brainburns.brainburns.domain.model;

/**
 * Created by arthan on 19.01.2017. | Project brainburns
 */
public class Desk {
    private long id;
    private String username;
    private String title;

    public Desk() {
    }

    public Desk(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
