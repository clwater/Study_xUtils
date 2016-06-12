package com.simpleweater.tools.dbmodel;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by yszsyf on 16/6/12.
 */

@Table(name = "province")
public class Table_Province {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name= "name",isId=true,autoGen=false)
    private String name;

    @Column(name = "id")
    private String id ;
}
