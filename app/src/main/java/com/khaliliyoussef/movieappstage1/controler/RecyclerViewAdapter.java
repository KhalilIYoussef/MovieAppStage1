package com.khaliliyoussef.movieappstage1.controler;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.khaliliyoussef.movieappstage1.R;
import com.khaliliyoussef.movieappstage1.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder>
{
//TODO list of type you wanna pass
    private List<Movie> movieList;
    //the context
    private Context context;
    //the list listener
    private ListItemClickListener mOnClickListener=null;


    //TODO obvious constructor will take thes theree paramaters
    public RecyclerViewAdapter(List<Movie> movieList, Context context, ListItemClickListener mOnClickListener)
    {
        this.movieList = movieList;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        //TODO here we put our inflation of the item in the recycler
        LayoutInflater  inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);

        //TODO pass this view to a view holder then return the viewHolder
       return  new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        //TODO put the data in each view
//examle holder.title.setText(movielist.get(position).title)

        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movieList.get(position).getPosterPath()).into(holder.imageView);


    }

    @Override
    public int getItemCount()
    {
        //pass the number of members on the Movielist
        return movieList.size();
    }

    //create an interface called ListItemListener and has one function called onListItemClick
    public interface ListItemClickListener
    {
        void onListItemClick(int clickedItemIndex);
    }
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //initialize the views
        ImageView imageView;
        public MovieViewHolder(View itemView)
        {
            //TODO put the views inside the item and cash them
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_main);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            //TODO implement a listener for the the list
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
