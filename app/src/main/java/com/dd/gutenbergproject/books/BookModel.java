package com.dd.gutenbergproject.books;

class BookModel {
    private String bookTitle;
    private String bookAuthor;
    private int bookImage;

    public BookModel(String bookTitle, String bookAuthor, int bookImage) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getImage() {
        return bookImage;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }
}
