package com.dd.gutenbergproject.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dd.gutenbergproject.R;

import java.util.ArrayList;
import java.util.List;

class BookListAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private final OnBookSelectListener listener;
    List<BookModel> list = new ArrayList<>();
    private Context context;

    public BookListAdapter(OnBookSelectListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookModel model = list.get(position);
        holder.setItem(model);
        holder.getTvBookTitle().setText(model.getBookTitle());
        holder.getTvBookAuthor().setText(model.getBookAuthor());
        Glide.with(context).load(model.getImage()).into(holder.getIvBookImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<BookModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
