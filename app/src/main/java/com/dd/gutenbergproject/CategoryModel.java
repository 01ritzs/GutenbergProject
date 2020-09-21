package com.dd.gutenbergproject;

class CategoryModel {
    private String title;
    private int image;

    public CategoryModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
