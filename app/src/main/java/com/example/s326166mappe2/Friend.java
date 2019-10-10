package com.example.s326166mappe2;

public class Friend {

    private long _ID;
    private String name;
    private String tlf;

    public Friend() {
        //Default constructor
    }

    public Friend(long _ID, String name, String tlf) {
        this._ID = _ID;
        this.name = name;
        this.tlf = tlf;
    }

    public Friend(String name, String tlf) {
        this.name = name;
        this.tlf = tlf;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
}
