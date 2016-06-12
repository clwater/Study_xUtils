package com.simpleweater.tools.model;

/**
 * Created by yszsyf on 16/6/12.
 */
public class City {
    private  String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    private String parent;
}
