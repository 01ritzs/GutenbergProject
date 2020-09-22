package com.dd.gutenbergproject.books;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        parent = findViewById(R.id.parent);
        category = getIntent().getStringExtra(Constants.IntentKeys.CATEGORY);
        initHeader();
        initRecyclerView();
        GetBooksTask task = new GetBooksTask();
        task.execute(category);
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
        etSearch.addTextChangedListener(textWatcher);
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

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            GetBooksTask task = new GetBooksTask();
            task.execute(category, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void onBookSelect(BookModel bookModel) {
        if (bookModel.getHtmlBook() != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bookModel.getHtmlBook()));
            startActivity(browserIntent);
        }
    }

    public class GetBooksTask extends AsyncTask<String, Void, BooksResponse> {

        @Override
        protected BooksResponse doInBackground(String[] objects) {
            String searchText = objects.length > 1 ? objects[1] : "";
            BooksResponse response = getBooks(objects[0], searchText);
            return response;
        }

        public BooksResponse getBooks(String category, String searchText) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://skunkworks.ignitesol.com:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            BookService service = retrofit.create(BookService.class);
            if (searchText == null) {
                searchText = "";
            }
            Call<BooksResponse> callSync = service.getBooks("image", category, searchText);

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
                        new GetBooksTask().execute(category);
                    }
                });
    }
}
