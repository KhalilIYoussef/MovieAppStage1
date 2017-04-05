package com.khaliliyoussef.movieappstage1.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.khaliliyoussef.movieappstage1.R;
import com.khaliliyoussef.movieappstage1.model.Movie;
import com.squareup.picasso.Picasso;



public class DetailsActivity extends AppCompatActivity
{
    TextView mOverview,mRelaseDate,mTitle,mVoteAvg;
    Movie movie=null;
    ImageView image;
    ImageButton ib_favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //TODO link it with xml da eltaby3y

        mOverview= (TextView) findViewById(R.id.tv_overview);
        mRelaseDate= (TextView) findViewById(R.id.tv_relase_date);
        mTitle= (TextView) findViewById(R.id.tv_title);
        mVoteAvg= (TextView) findViewById(R.id.tv_vote_average);
        image= (ImageView) findViewById(R.id.img_details);
        ib_favorite= (ImageButton) findViewById(R.id.ib_favorite);

        //TODO get the data from the mainActivity
        Intent intent = this.getIntent();
        movie=intent.getParcelableExtra(Intent.EXTRA_TEXT);

        if(movie!=null)
        {
            mTitle.setText(movie.getOriginalTitle());
            mOverview.setText(movie.getOverview());
            mRelaseDate.setText(movie.getReleaseDate());
            mVoteAvg.setText(movie.getVoteAverage() + "/10");
            Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).into(image);

        }
        }

}
