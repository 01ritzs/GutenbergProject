package com.dd.gutenbergproject.books;

import retrofit2.Call;
import retrofit2.http.GET;

interface BookService {
    @GET("/books")
    Call<BooksResponse> getBooks();
}
