package com.dd.gutenbergproject.books;

class BookModel {
    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private String htmlBook;

    public BookModel(String bookTitle, String bookAuthor, String bookImage, String htmlBook) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.htmlBook = htmlBook;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getImage() {
        return bookImage;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getHtmlBook() {
        return htmlBook;
    }

    public void setHtmlBook(String htmlBook) {
        this.htmlBook = htmlBook;
    }
}
