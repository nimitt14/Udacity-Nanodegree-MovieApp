package com.example.nimitt.movie;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

//Handling the network call via Retrofit
public class MovieApi implements Callback<MovieList> {

    public static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/";

    public List<Result> moviesList;
    public RecyclerViewAdapter mAdapter;
    private String mSorting;
    private Context mContext;

    //to display the first page discover/movie
    public void getMoviesDescription(RecyclerViewAdapter mAdapter, List<Result> result, MainActivity mainActivity){

        this.mAdapter=mAdapter;
        moviesList=result;
        mContext=mainActivity;

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<MovieList> call = apiInterface.listOfMoviesDescription(ApiKey.API_KEY,
                "en-US", "vote_average.desc","false","false",1);
        call.enqueue(this);
        }

        //Displays movie on popular or top_rated preference
        public void getMovies(RecyclerViewAdapter mAdapter, List<Result> result,int checker){

        this.mAdapter=mAdapter;
        moviesList=result;

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        //if checker = 1, display Popular movies
        if(checker==1) {
            Call<MovieList> call = apiInterface.listOfMovies("popular", ApiKey.API_KEY, "en-US", 1);
            call.enqueue(this);
        }
        //else display Top Rated movies
        else{
            Call<MovieList> call = apiInterface.listOfMovies("top_rated", ApiKey.API_KEY, "en-US", 1);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<MovieList> call, Response<MovieList> response) {

        if (response.isSuccessful()){
            MovieList movies=response.body();
            mAdapter.updateMovieList(movies.getResults());
        }
        else {
            try {
                Log.d(TAG, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<MovieList> call, Throwable t) {
    //        t.printStackTrace();
        //Toast.makeText(MovieApi.this, "Error", Toast.LENGTH_SHORT).show();

        // If there is Internet connection issue
        if (t instanceof IOException) {
            Toast.makeText(mContext, "Network Failure, Connect to Internet !", Toast.LENGTH_SHORT).show();

        }
        //In case of conversion problem
        else {
            Toast.makeText(mContext, "Conversion issue! ", Toast.LENGTH_SHORT).show();

        }

    }

}
