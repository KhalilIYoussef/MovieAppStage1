package com.khaliliyoussef.movieappstage1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.khaliliyoussef.movieappstage1.data.Movie;
import com.khaliliyoussef.movieappstage1.interfaces.AsyncTaskCompleteListener;
import com.khaliliyoussef.movieappstage1.utilities.MovieAsyncTask;
import com.khaliliyoussef.movieappstage1.utilities.MovieJsonUtils;
import com.khaliliyoussef.movieappstage1.utilities.recyclers.HomeRecyclerViewAdapter;

import org.json.JSONException;

import java.util.List;


public class MainFragment extends Fragment implements AsyncTaskCompleteListener,HomeRecyclerViewAdapter.ListItemClickListener {

    String popular ="http://api.themoviedb.org/3/movie/popular?api_key=d47bb209ca59541c9a2a46c99ccdd528";
    String topRated ="http://api.themoviedb.org/3/movie/top_rated?api_key=d47bb209ca59541c9a2a46c99ccdd528";
    List<Movie> movies;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main,container);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_main);
        new MovieAsyncTask(this,popular).execute();
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.topRated)
        {
            new MovieAsyncTask(this,popular).execute();
        }
        else if(item.getItemId()==R.id.mostPopular)
        {
            new MovieAsyncTask(this,topRated).execute();
        }
        return true;
    }

    @Override
    public void onTaskComplete(String result) throws JSONException {
        if(result!=null) {
            movies= MovieJsonUtils.getMoviesDataFromJson(result);
            HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(movies,getActivity(),this);
            RecyclerView.LayoutManager  manager= new GridLayoutManager(getActivity(),2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
//        startActivity(new Intent(getActivity(),DetailsActivity.class));
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,movies.get(clickedItemIndex));
        startActivity(intent);
    }
}
