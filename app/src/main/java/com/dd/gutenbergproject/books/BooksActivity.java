package com.dd.gutenbergproject.books;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.gutenbergproject.R;
import com.dd.gutenbergproject.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksActivity extends AppCompatActivity implements OnBookSelectListener {

    private ConstraintLayout parent;
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
        parent = findViewById(R.id.parent);
        initRecyclerView();
        initHeader();
        GetBooksTask task = new GetBooksTask();
        task.execute();
    }

    private void initRecyclerView() {
        rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new GridLayoutManager(this, 3));
        rvBooks.setHasFixedSize(true);
        adapter = new BookListAdapter(this);
        rvBooks.setAdapter(adapter);
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

    public class GetBooksTask extends AsyncTask<String, Void, BooksResponse> {

        @Override
        protected BooksResponse doInBackground(String[] objects) {
            BooksResponse response = getBooks();
            return response;
        }

        public BooksResponse getBooks() {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://skunkworks.ignitesol.com:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            BookService service = retrofit.create(BookService.class);
            Call<BooksResponse> callSync = service.getBooks();

            try {
                Response<BooksResponse> response = callSync.execute();
                BooksResponse booksResponse = response.body();
                Log.d("booksResponse", booksResponse.toString());
                return booksResponse;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(BooksResponse booksResponse) {
            super.onPostExecute(booksResponse);
            if (booksResponse == null) {
                showMessageForFailure();
            } else {
                populateBooks(booksResponse);
            }
        }
    }

    private void populateBooks(BooksResponse booksResponse) {
        BookMapper mapper = new BookMapper();
        List<BookModel> books = mapper.convert(booksResponse);
        adapter.setList(books);
    }

    private void showMessageForFailure() {
        Snackbar.make(parent, getString(R.string.something_went_wrong),
                Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.retry),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new GetBooksTask().execute();
                    }
                });
    }
}
