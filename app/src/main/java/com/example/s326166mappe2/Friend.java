package com.example.s326166mappe2;

public class Friend extends DataModel{

    private String name;
    private String ph_no;

    public Friend() {
    }

    public Friend(String name, String ph_no) {
        this.name = name;
        this.ph_no = ph_no;
    }

    public Friend(long _ID, String name, String ph_no) {
        super(_ID);
        this.name = name;
        this.ph_no = ph_no;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }
}
