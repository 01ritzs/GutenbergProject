package com.dd.gutenbergproject.books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface BookService {
    @GET("/books")
    Call<BooksResponse> getBooks(
            @Query("mime_type") String mimeType,
            @Query("topic") String topic,
            @Query("search") String search
    );
}
