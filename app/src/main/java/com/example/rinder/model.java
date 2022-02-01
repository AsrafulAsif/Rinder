package com.example.rinder;

public class model {
    public String uid,email,name,gender,subject;

    public model() {
    }

    public model(String uid, String email, String name, String gender, String subject) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.subject = subject;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
