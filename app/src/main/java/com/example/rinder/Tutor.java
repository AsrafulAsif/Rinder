package com.example.rinder;

public class Tutor {

    String uid ,tutorname,tutoremail,tutorgender,tutoruniversity,tsubject;

    public Tutor() {
    }

    public Tutor(String uid, String tutorname, String tutoremail, String tutorgender, String tutoruniversity, String tsubject) {
        this.uid = uid;
        this.tutorname = tutorname;
        this.tutoremail = tutoremail;
        this.tutorgender = tutorgender;
        this.tutoruniversity = tutoruniversity;
        this.tsubject = tsubject;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTutorname() {
        return tutorname;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public String getTutoremail() {
        return tutoremail;
    }

    public void setTutoremail(String tutoremail) {
        this.tutoremail = tutoremail;
    }

    public String getTutorgender() {
        return tutorgender;
    }

    public void setTutorgender(String tutorgender) {
        this.tutorgender = tutorgender;
    }

    public String getTutoruniversity() {
        return tutoruniversity;
    }

    public void setTutoruniversity(String tutoruniversity) {
        this.tutoruniversity = tutoruniversity;
    }

    public String getTsubject() {
        return tsubject;
    }

    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
    }
}
