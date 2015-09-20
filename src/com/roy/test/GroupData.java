package com.roy.test;

import grandroid.database.Identifiable;
import grandroid.database.Table;
import java.util.Date;

@Table("test")
public class GroupData implements Identifiable {

    protected Integer _id;
    protected String name;
    protected String address;
    protected Date time;

  

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

}