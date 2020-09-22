package com.dd.gutenbergproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final OnCategorySelectListener listener;
    List<CategoryModel> list = new ArrayList<>();

    public CategoryListAdapter(OnCategorySelectListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_catergory, parent, false);
        return new CategoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel model = list.get(position);
        holder.setItem(model);
        holder.getTvTitle().setText(model.getTitle());
        holder.getIvLeft().setBackgroundResource(model.getImage());
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
