package com.dd.gutenbergproject.books;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Format {
    @SerializedName("image/jpeg")
    @Expose
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
