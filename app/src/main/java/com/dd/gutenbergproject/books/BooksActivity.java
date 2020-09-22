package com.dd.gutenbergproject.books;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.gutenbergproject.R;
import com.dd.gutenbergproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity implements OnBookSelectListener {

    private TextView tvSearchTitle;
    private ImageView ivBack;
    private EditText etSearch;
    private ImageView ivCancel;
    private RecyclerView rvBooks;
    BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new GridLayoutManager(this, 3));
        rvBooks.setHasFixedSize(true);
        adapter = new BookListAdapter(this);
        adapter.setList(getList());
        rvBooks.setAdapter(adapter);
        initHeader();
    }

    public List<BookModel> getList() {
        List<BookModel> models = new ArrayList();
        models.add(new BookModel(getString(R.string.fiction), "Ritesh", R.drawable.ic_fiction));
        models.add(new BookModel(getString(R.string.drama), "Abhishek", R.drawable.ic_drama));
        models.add(new BookModel(getString(R.string.humor), "Rana", R.drawable.ic_humour));
        models.add(new BookModel(getString(R.string.politics), "Digu", R.drawable.ic_politics));
        models.add(new BookModel(getString(R.string.adventure), "Golu", R.drawable.ic_adventure));
        models.add(new BookModel(getString(R.string.history), "Lalu", R.drawable.ic_history));
        models.add(new BookModel(getString(R.string.philosophy), "Ravi", R.drawable.ic_philosophy));
        return models;
    }

    private void initHeader() {
        tvSearchTitle = findViewById(R.id.tvSearchTitle);
        ivBack = findViewById(R.id.ivBack);
        ivCancel = findViewById(R.id.ivCancel);
        etSearch = findViewById(R.id.etSearch);
        String category = getIntent().getStringExtra(Constants.IntentKeys.CATEGORY);
        tvSearchTitle.setText(category);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });
    }

    @Override
    public void onBookSelect(BookModel bookModel) {

    }
}