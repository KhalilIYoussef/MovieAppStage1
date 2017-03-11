package com.khaliliyoussef.movieappstage1;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaliliyoussef.movieappstage1.data.Movie;
import com.khaliliyoussef.movieappstage1.data.Review;
import com.khaliliyoussef.movieappstage1.interfaces.AsyncTaskCompleteListener;
import com.khaliliyoussef.movieappstage1.utilities.recyclers.HomeRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;




public class DetailsFragment extends Fragment implements AsyncTaskCompleteListener,HomeRecyclerViewAdapter.ListItemClickListener  {

    TextView mOverview,mRelaseDate,mTitle,mVoteAvg;
    Movie movie=null;
    ImageView image;
    ImageButton ib_favorite;
    List<Review> reviews=null;
    List<String> trailers=null;
    RecyclerView reviewsRecyclerView,trailersRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);
        mOverview= (TextView) view.findViewById(R.id.tv_overview);
        mRelaseDate= (TextView) view.findViewById(R.id.tv_relase_date);
        mTitle= (TextView) view.findViewById(R.id.tv_title);
        mVoteAvg= (TextView) view.findViewById(R.id.tv_vote_average);
        image= (ImageView) view.findViewById(R.id.img_details);
        ib_favorite= (ImageButton) view.findViewById(R.id.ib_favorite);

        Intent intent = getActivity().getIntent();
            movie=intent.getParcelableExtra(Intent.EXTRA_TEXT);
        if(movie!=null) {
            mTitle.setText(movie.getOriginal_title());
            mOverview.setText(movie.getOverview());
            mRelaseDate.setText(movie.getRelease_date());
            mVoteAvg.setText(movie.getVote_average() + "/10");
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185/"+movie.getPoster_path()).into(image);
        }


        return view;
    }

    @Override
    public void onTaskComplete(String result) throws JSONException {
        if(result!=null) {
   }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Uri webPage = Uri.parse(""+trailers.get(clickedItemIndex));
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
