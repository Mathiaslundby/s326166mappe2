package com.example.s326166mappe2;

public class Restaurant {
    private int _ID;
    private String name;
    private String address;
    private String tlf;
    private String type;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String tlf, String type) {
        this.name = name;
        this.address = address;
        this.tlf = tlf;
        this.type = type;
    }

    public Restaurant(int _ID, String name, String address, String tlf, String type) {
        this._ID = _ID;
        this.name = name;
        this.address = address;
        this.tlf = tlf;
        this.type = type;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
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

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
