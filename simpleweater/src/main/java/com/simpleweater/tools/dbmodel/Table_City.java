package com.simpleweater.tools.dbmodel;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by yszsyf on 16/6/12.
 */

@Table(name = "city")
public class Table_City {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name= "name",isId=true,autoGen=false)
    private String name;

}
