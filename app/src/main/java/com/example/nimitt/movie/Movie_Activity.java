package com.example.nimitt.movie;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

//To display the details of particular movie
//Displaying Movies Original Title,
//                  Poster,
//                  Release Date,
//                  Plot,
//                  Rating
public class Movie_Activity extends AppCompatActivity {

    private TextView title, release, vote, plot;
    private ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        title = findViewById(R.id.title_id);
        release = findViewById(R.id.release_date_id);
        vote = findViewById(R.id.vote_id);
        plot = findViewById(R.id.plot_id);
        thumbnail = findViewById(R.id.thumbnail_id);

        Intent intent = getIntent();
        if (intent.hasExtra("Passed")) {

            Result result = (Result) intent.getParcelableExtra("Passed");

            Picasso.with(this)
                    .load(PosterUtils.buildImagePath(result.getPosterPath(), "w185"))
                    .into(thumbnail);

            title.setText(result.getOriginalTitle());
            release.setText(result.getReleaseDate());
            vote.setText(result.getVoteAverage());
            plot.setText(result.getOverview());
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}

