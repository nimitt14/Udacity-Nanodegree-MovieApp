package com.example.nimitt.movie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Interface defining the functions to be performed
//In our case listOfMovies function handles popular and top rated movies
//and listOfMoviesDescription function handles discover/movies from movieDB
//which is displayed on first screen
public interface ApiInterface {

    //sort id dynamic gets its value as popular or top_rated
    @GET("movie/{sort}")
    Call<MovieList> listOfMovies(@Path("sort") String SortingKey,
                                 @Query("api_key") String apiKey,
                                 @Query("language") String language,
                                 @Query("page") int page
    );

    @GET("discover/movie")
    Call<MovieList> listOfMoviesDescription(

            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sort_by,
            @Query("include_adult") String include_adult,
            @Query("include_video") String include_video,
            @Query("page") int page
    );


}
