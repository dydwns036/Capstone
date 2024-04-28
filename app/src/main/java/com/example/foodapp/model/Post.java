package com.example.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private int id;
    private String avatarUrl;
    private String username;
    private String date;
    private String title;
    private String content;
    private List<String> imageUrls;
    private int isRecipe;

    public Post(int id, String avatarUrl, String username, String date, String title, String content, List<String> imageUrls, int isRecipe) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.date = date;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
        this.isRecipe = isRecipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getIsRecipe() {
        return isRecipe;
    }

    public void setIsRecipe(int isRecipe) {
        this.isRecipe = isRecipe;
    }
}
