package com.example.rinder;

public class Users {
    public String uid,name,email,gender,subject;

    public Users() {
    }

    public Users(String uid, String name, String email, String gender, String subject) {
        this.uid= uid;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.subject = subject;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
