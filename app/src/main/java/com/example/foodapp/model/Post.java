package com.example.foodapp.model;

import java.util.List;

public class Post {
    private String avatarUrl;
    private String username;
    private String date;
    private String content;
    private List<String> imageUrls;

    public Post(String avatarUrl, String username, String date, String content, List<String> imageUrls) {
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.date = date;
        this.content = content;
        this.imageUrls = imageUrls;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
