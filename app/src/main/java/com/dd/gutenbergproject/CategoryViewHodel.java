package com.dd.gutenbergproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CategoryViewHodel extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private ImageView ivLeft;

    public CategoryViewHodel(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivLeft = itemView.findViewById(R.id.ivLeft);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }
}
