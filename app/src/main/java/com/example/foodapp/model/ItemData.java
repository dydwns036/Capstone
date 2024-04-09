package com.example.foodapp.model;
// Tạo lớp ItemData.java
public class ItemData {
    private int imageResId;
    private String itemgridName;

    public ItemData(int imageResId, String itemgridName) {
        this.imageResId = imageResId;
        this.itemgridName = itemgridName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getItemgridName() {
        return itemgridName;
    }


}
