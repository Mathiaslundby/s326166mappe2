package com.example.s326166mappe2;

public class Event {

    private long _ID;
    private long rest;
    private String time;

    public Event() {

    }

    public Event(long rest, String time) {
        this.rest = rest;
        this.time = time;
    }

    public Event(long _ID, long rest, String time) {
        this._ID = _ID;
        this.rest = rest;
        this.time = time;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public long getRest() {
        return rest;
    }

    public void setRest(long rest) {
        this.rest = rest;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}