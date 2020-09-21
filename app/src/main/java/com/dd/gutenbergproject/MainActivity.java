package com.dd.gutenbergproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategoryList;
    CategoryListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategoryList = findViewById(R.id.rvCategoryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategoryList.setHasFixedSize(true);
        rvCategoryList.setLayoutManager(layoutManager);
        adapter = new CategoryListAdapter();
        adapter.setList(getList());
        rvCategoryList.setAdapter(adapter);

    }

    public List<CategoryModel> getList() {
        List<CategoryModel> models = new ArrayList();
        models.add(new CategoryModel("Friction", R.drawable.ic_fiction));
        models.add(new CategoryModel("Drama", R.drawable.ic_drama));
        models.add(new CategoryModel("Humor", R.drawable.ic_humour));
        models.add(new CategoryModel("Politics", R.drawable.ic_politics));
        models.add(new CategoryModel("Adventure", R.drawable.ic_adventure));
        models.add(new CategoryModel("History", R.drawable.ic_history));
        models.add(new CategoryModel("Philosophy", R.drawable.ic_philosophy));
        return models;
    }
}