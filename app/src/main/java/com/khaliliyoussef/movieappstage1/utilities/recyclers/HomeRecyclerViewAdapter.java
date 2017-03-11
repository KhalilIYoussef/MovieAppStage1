package com.khaliliyoussef.movieappstage1.utilities.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khaliliyoussef.movieappstage1.R;
import com.khaliliyoussef.movieappstage1.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;



public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MovieViewHolder> {

    private List<Movie> movieList;;
    private Context context;
    private ListItemClickListener mOnClickListener=null;

    public HomeRecyclerViewAdapter(List<Movie> movieList, Context context, ListItemClickListener mOnClickListener) {
        this.movieList = movieList;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_main);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movieList.get(listIndex).getPoster_path()).into(imageView);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
