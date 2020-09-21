package com.dd.gutenbergproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHodel> {

    List<CategoryModel> list = new ArrayList<>();

    @NonNull
    @Override
    public CategoryViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_catergory, parent, false);
        return new CategoryViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHodel holder, int position) {
        holder.getTvTitle().setText(list.get(position).getTitle());
        holder.getIvLeft().setBackgroundResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CategoryModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
