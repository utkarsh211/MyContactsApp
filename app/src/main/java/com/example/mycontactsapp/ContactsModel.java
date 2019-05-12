package com.example.mycontactsapp;

public class ContactsModel {
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_image_thumbnail() {
        return user_image_thumbnail;
    }

    public String getUser_image() {
        return user_image;
    }

    private String name;
    private String number;
    private String email;
    private String user_image_thumbnail;
    private String user_image;

    public ContactsModel(String name, String number, String email, String user_image_thumbnail, String user_image) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.user_image_thumbnail = user_image_thumbnail;
        this.user_image = user_image;
    }
}
