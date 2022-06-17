package com.safetravel.safetravel;


public class User {
    private String email;
    private String name;
    private String lastname;
    private String pass;
    private String date;

    public User(String email, String name, String lastname, String pass) {
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.pass = pass;
        this.date = date;
    }

    public User() {
        this.email = "none";
        this.name = "none";
        this.lastname = "none";
        this.pass = "none";
        //this.date = "none";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
