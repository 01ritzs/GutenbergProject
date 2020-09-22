package com.dd.gutenbergproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.dd.gutenbergproject.books.BooksActivity;
import com.dd.gutenbergproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCategorySelectListener {

    private RecyclerView rvCategoryList;
    CategoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCategoryList();
    }

    private void initCategoryList() {
        rvCategoryList = findViewById(R.id.rvCategoryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategoryList.setHasFixedSize(true);
        rvCategoryList.setLayoutManager(layoutManager);
        adapter = new CategoryListAdapter(this);
        adapter.setList(getList());
        rvCategoryList.setAdapter(adapter);
    }

    public List<CategoryModel> getList() {
        List<CategoryModel> models = new ArrayList();
        models.add(new CategoryModel(getString(R.string.fiction), R.drawable.ic_fiction));
        models.add(new CategoryModel(getString(R.string.drama), R.drawable.ic_drama));
        models.add(new CategoryModel(getString(R.string.humor), R.drawable.ic_humour));
        models.add(new CategoryModel(getString(R.string.politics), R.drawable.ic_politics));
        models.add(new CategoryModel(getString(R.string.adventure), R.drawable.ic_adventure));
        models.add(new CategoryModel(getString(R.string.history), R.drawable.ic_history));
        models.add(new CategoryModel(getString(R.string.philosophy), R.drawable.ic_philosophy));
        return models;
    }

    @Override
    public void onCategorySelect(CategoryModel categoryModel) {
        Intent intent = new Intent(this, BooksActivity.class);
        intent.putExtra(Constants.IntentKeys.CATEGORY, categoryModel.getTitle());
        startActivity(intent);
    }
}