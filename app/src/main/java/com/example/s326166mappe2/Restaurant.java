package com.example.s326166mappe2;

public class Restaurant extends DataModel {

    private String name;
    private String address;
    private String ph_no;
    private String type;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String ph_no, String type) {
        this.name = name;
        this.address = address;
        this.ph_no = ph_no;
        this.type = type;
    }

    public Restaurant(long _ID, String name, String address, String ph_no, String type) {
        super(_ID);
        this.name = name;
        this.address = address;
        this.ph_no = ph_no;
        this.type = type;
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

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
