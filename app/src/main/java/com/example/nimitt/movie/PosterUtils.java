package com.example.nimitt.movie;

import android.net.Uri;

//Class for building the Image Path
//containing String BASE_IMAGE_URL as the base image URL
public class PosterUtils {

    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";

    public static String buildImagePath(String path,String size)
    {
        return Uri.parse(BASE_IMAGE_URL)
                .buildUpon()
                .appendPath(size)
                .appendEncodedPath(path)
                .build()
                .toString();
    }
}
