package com.nhn.fitness.data.model;

public class Message {
    private int id;
    private String message;
    private String time;
    private int type;

    public Message(int id, String message, String time, int type) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.type = type;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
