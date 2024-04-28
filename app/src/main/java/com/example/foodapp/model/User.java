package com.example.myapplication.model;
public class User {
    private int userId;
    private String username;
    private String userAccname;
    private String email;
    private String avatarImage;
    private String coverImage;
    private int isAdmin;

    public User(int userId, String username, String userAccname, String email, String avatarImage, String coverImage, int isAdmin) {
        this.userId = userId;
        this.username = username;
        this.userAccname = userAccname;
        this.email = email;
        this.avatarImage = avatarImage;
        this.coverImage = coverImage;
        this.isAdmin = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserAccname() {
        return userAccname;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public int getIsAdmin() {
        return isAdmin;
    }
}
