package com.example.trihandayani.trihandayani_1202154321_modul5;

/**
 * Created by HMJ on 3/25/2018.
 */

public class ToDoItem {

    private int id;
    private String name, desc, priority;

    public ToDoItem() {

    }

    public ToDoItem(String name, String desc, String priority) {
        this.name = name;
        this.desc = desc;
        this.priority = priority;
    }

    public ToDoItem(int id, String name, String desc, String priority) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.priority = priority;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    //getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPriority() {
        return priority;
    }
}
