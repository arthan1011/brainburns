package com.brainburns.brainburns.domain.model;

/**
 * Created by arthan on 19.01.2017. | Project brainburns
 */
public class Desk {
    private long id;
    private String name;

    public Desk() {
    }

    public Desk(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
