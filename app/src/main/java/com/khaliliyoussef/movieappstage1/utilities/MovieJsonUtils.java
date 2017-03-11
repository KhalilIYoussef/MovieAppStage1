package com.khaliliyoussef.movieappstage1.utilities;

import com.khaliliyoussef.movieappstage1.data.Movie;
import com.khaliliyoussef.movieappstage1.data.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class MovieJsonUtils {

       public static List<Movie> getMoviesDataFromJson(String movieJsonStr) {
           final String LIST = "results";
           final String ORIGINAL_TITLE = "original_title";
           final String MOVIE_POSTER = "poster_path";
           final String OVERVIEW = "overview";
           final String RATING = "vote_average";
           final String RELEASE_DATE = "release_date";
           final String FILM_ID = "id";
           try {
               JSONObject movieJson = new JSONObject(movieJsonStr);
               JSONArray moviesArray = movieJson.getJSONArray(LIST);

               Movie mov;
               List<Movie> moviesList = new ArrayList<>();
               for (int i = 0; i < moviesArray.length(); i++) {
                   mov = new Movie();
                   JSONObject movie = moviesArray.getJSONObject(i);
                   mov.setPoster_path(movie.getString(MOVIE_POSTER));
                   mov.setOriginal_title(movie.getString(ORIGINAL_TITLE));
                   mov.setOverview(movie.getString(OVERVIEW));
                   mov.setRelease_date(movie.getString(RELEASE_DATE));
                   mov.setVote_average(movie.getString(RATING));
                   mov.setId(movie.getString(FILM_ID));
                   moviesList.add(mov);
               }
               return moviesList;
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           return null;
       }

    public static List<Review> getReviewsDataFromJson(String movieJsonStr) throws JSONException {

        if(movieJsonStr!=null)
        {
        final String LIST = "results";
        final String AUTHOR = "author";
        final String CONTENT = "content";

        List<Review> lst = new ArrayList<Review>();
        Review review;
        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray moviesArray = movieJson.getJSONArray(LIST);

        for (int i = 0; i < moviesArray.length(); i++) {
            review = new Review();
            JSONObject trailers = moviesArray.getJSONObject(i);
            review.setAuther(trailers.getString(AUTHOR));
            review.setReview(trailers.getString(CONTENT));
            lst.add(review);
        }
        return lst;
    }
        return null;
    }

    public static List<String> getTrailersDataFromJson(String movieJsonStr) throws JSONException {

        if(movieJsonStr!=null) {
            final String LIST = "results";
            final String KEY = "key";

            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray moviesArray = movieJson.getJSONArray(LIST);

            List<String> Trailers = new ArrayList<String>();
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject trailers = moviesArray.getJSONObject(i);
                Trailers.add(trailers.getString(KEY));
            }
            return Trailers;
        }
        return null;
    }
}