package com.dd.gutenbergproject.books;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public List<BookModel> convert(BooksResponse booksResponse) {
        List<BookModel> bookModels = new ArrayList<>();
        List<Result> books = booksResponse.getResults();
        if (books != null) {
            int size = books.size();
            for (int i = 0; i < size; i++) {
                Result book = books.get(i);
                String title = book.getTitle();
                String author = "";
                if (book.getAuthors() != null && book.getAuthors().size() > 0 && book.getAuthors().get(0) != null) {
                    author = book.getAuthors().get(0).getName();
                }
                String image = "";
                if (book.getFormat() != null && book.getFormat().getImageUrl() != null) {
                    image = book.getFormat().getImageUrl();
                }
                String htmlBook = "";
                if (book.getFormat() != null && book.getFormat().getWebBook() != null) {
                    htmlBook = book.getFormat().getWebBook();
                }
                BookModel bookModel = new BookModel(title, author, image, htmlBook);
                bookModels.add(bookModel);
            }
        }
        return bookModels;
    }
}
