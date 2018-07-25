package com.example.nimitt.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nimitt.movie.MovieApi.*;

// This is the first Activity making Recycler View,
// calling the function getMoviesDescription to create the first UI page
// and creating the options menu.
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Result> movies = new ArrayList<>();
    MovieApi movieApi=new MovieApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter = new RecyclerViewAdapter(this, movies);
        recyclerView.setAdapter(mAdapter);

        movieApi.getMoviesDescription(mAdapter, movies,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.popular:
                movieApi.getMovies(mAdapter, movies,1);
                return true;

            case R.id.rated:
                movieApi.getMovies(mAdapter,movies,0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
