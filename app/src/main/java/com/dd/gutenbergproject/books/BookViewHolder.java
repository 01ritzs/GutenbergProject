package com.dd.gutenbergproject.books;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.gutenbergproject.R;

class BookViewHolder extends RecyclerView.ViewHolder {

    private final OnBookSelectListener listener;
    private TextView tvBookTitle;
    private ImageView ivBook;
    private TextView tvBookAuthor;
    private BookModel selectedItem;

    public BookViewHolder(@NonNull View itemView, final OnBookSelectListener listener) {
        super(itemView);
        tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
        ivBook = itemView.findViewById(R.id.ivBook);
        tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
        this.listener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBookSelect(selectedItem);
            }
        });
    }

    public TextView getTvBookTitle() {
        return tvBookTitle;
    }

    public TextView getTvBookAuthor() {
        return tvBookAuthor;
    }

    public ImageView getIvBookImage() {
        return ivBook;
    }


    public void setItem(BookModel model) {
        this.selectedItem = model;
    }
}

interface OnBookSelectListener {
    void onBookSelect(BookModel bookModel);
}