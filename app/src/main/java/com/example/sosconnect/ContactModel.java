package com.example.sosconnect;

public class ContactModel {
    int img;
    String name, number, email, msg;

    public ContactModel(int img, String name, String number, String msg, String email) {
        this.img = img;
        this.name = name;
        this.number = number;
        this.msg = msg;
        this.email = email;
    }
}
