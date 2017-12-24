package com.example.dragnic.bpums;

/**
 * Created by Dragnic on 12/24/2017.
 */

public class User {
    private int id;
    private String username;
    private String password;
    private int role_id;
//geteri i seteri
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
 //konstruktor(i)
    public User (int id, String username, String password, int role_id){
    this.id = id;
    this.username = username;
    this.password = password;
    this.role_id = role_id;
    }

    public User(){}
}
