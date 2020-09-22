package com.dd.gutenbergproject.books;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Format {
    @SerializedName("image/jpeg")
    @Expose
    private String imageUrl;
    @SerializedName("text/html; charset=utf-8")
    @Expose
    private String webBook;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebBook() {
        return webBook;
    }

    public void setWebBook(String webBook) {
        this.webBook = webBook;
    }
}
