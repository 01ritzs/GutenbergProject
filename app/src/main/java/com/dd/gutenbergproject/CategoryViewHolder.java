package com.dd.gutenbergproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CategoryViewHolder extends RecyclerView.ViewHolder {

    private final OnCategorySelectListener listener;
    private TextView tvTitle;
    private ImageView ivLeft;
    private CategoryModel selectedItem;

    public CategoryViewHolder(@NonNull View itemView, final OnCategorySelectListener listener) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivLeft = itemView.findViewById(R.id.ivLeft);
        this.listener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCategorySelect(selectedItem);
            }
        });
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public void setItem(CategoryModel model) {
        this.selectedItem = model;
    }
}

interface OnCategorySelectListener {
    void onCategorySelect(CategoryModel categoryModel);
}