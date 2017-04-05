package com.khaliliyoussef.movieappstage1.controler;
import com.khaliliyoussef.movieappstage1.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public final class MovieJsonUtils {

       public static List<Movie> getMoviesDataFromJson(String movieJsonStr) {

           final String ORIGINAL_TITLE = "original_title";
           final String MOVIE_POSTER = "poster_path";
           final String OVERVIEW = "overview";
           final String RATING = "vote_average";
           final String RELEASE_DATE = "release_date";
           final String FILM_ID = "id";
           try {

               JSONObject movieJson = new JSONObject(movieJsonStr);
               JSONArray moviesArray = movieJson.getJSONArray("results");

               Movie mov;
               List<Movie> moviesList = new ArrayList<>();
               for (int i = 0; i < moviesArray.length(); i++) {
                   mov = new Movie();
                   JSONObject movie = moviesArray.getJSONObject(i);
                   mov.setPosterPath(movie.getString(MOVIE_POSTER));
                   mov.setOriginalTitle(movie.getString(ORIGINAL_TITLE));
                   mov.setOverview(movie.getString(OVERVIEW));
                   mov.setReleaseDate(movie.getString(RELEASE_DATE));
                   mov.setVoteAverage(movie.getDouble(RATING));
                   mov.setId(movie.getLong(FILM_ID));
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

}