package com.example.rinder;

public class model2 {
    String tsubject,tutoremail,tutorgender,tutorname,tutoruniversity;

    public model2() {
    }

    public model2(String tsubject, String tutoremail, String tutorgender, String tutorname, String tutoruniversity) {
        this.tsubject = tsubject;
        this.tutoremail = tutoremail;
        this.tutorgender = tutorgender;
        this.tutorname = tutorname;
        this.tutoruniversity = tutoruniversity;
    }

    public String getTsubject() {
        return tsubject;
    }

    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
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

    public String getTutorname() {
        return tutorname;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public String getTutoruniversity() {
        return tutoruniversity;
    }

    public void setTutoruniversity(String tutoruniversity) {
        this.tutoruniversity = tutoruniversity;
    }
}
