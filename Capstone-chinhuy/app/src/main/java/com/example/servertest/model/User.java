package com.example.servertest.model;

import java.io.Serializable;

public class User implements Serializable {
    private int user_id;
    private String name;
    private String account;
    private String email;
    private String avatar_image;
    private int is_admin;


    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", avatar_image='" + avatar_image + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
    public User(int user_id, String name, String account, String email, String avatar_image, int is_admin) {
        this.user_id = user_id;
        this.name = name;
        this.account = account;
        this.email = email;
        this.avatar_image = avatar_image;
        this.is_admin = is_admin;
    }


    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return name;
    }

    public String getUseraccname() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarImage() {
        return avatar_image;
    }

    public int getIsAdmin() {
        return is_admin;
    }
}
