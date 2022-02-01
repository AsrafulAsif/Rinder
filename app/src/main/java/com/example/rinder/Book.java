package com.example.rinder;

public class Book {
    String uid,bookName,bookMail,bookAddress,bookPrice,bookComment;

    public Book() {
    }

    public Book(String uid, String bookName, String bookMail, String bookAddress, String bookPrice, String bookComment) {
        this.uid = uid;
        this.bookName = bookName;
        this.bookMail = bookMail;
        this.bookAddress = bookAddress;
        this.bookPrice = bookPrice;
        this.bookComment = bookComment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookMail() {
        return bookMail;
    }

    public void setBookMail(String bookMail) {
        this.bookMail = bookMail;
    }

    public String getBookAddress() {
        return bookAddress;
    }

    public void setBookAddress(String bookAddress) {
        this.bookAddress = bookAddress;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookComment() {
        return bookComment;
    }

    public void setBookComment(String bookComment) {
        this.bookComment = bookComment;
    }
}
