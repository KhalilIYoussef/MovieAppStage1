package com.khaliliyoussef.movieappstage1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.khaliliyoussef.movieappstage1.R;
import com.khaliliyoussef.movieappstage1.model.Movie;
import com.khaliliyoussef.movieappstage1.interfaces.AsyncTaskCompleteListener;
import com.khaliliyoussef.movieappstage1.controler.MovieAsyncTask;
import com.khaliliyoussef.movieappstage1.controler.MovieJsonUtils;
import com.khaliliyoussef.movieappstage1.controler.RecyclerViewAdapter;
import org.json.JSONException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskCompleteListener,RecyclerViewAdapter.ListItemClickListener{
    String popular ="http://api.themoviedb.org/3/movie/popular?api_key=d47bb209ca59541c9a2a46c99ccdd528";
    String topRated ="http://api.themoviedb.org/3/movie/top_rated?api_key=d47bb209ca59541c9a2a46c99ccdd528";
    List<Movie> movies;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        new MovieAsyncTask(this).execute(popular);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mostPopular)
        {
            new MovieAsyncTask(this).execute(popular);
        }
        else if(item.getItemId()==R.id.topRated)
        {
            new MovieAsyncTask(this).execute(topRated);
        }

        return true;
    }
    @Override
    public void onListItemClick(int clickedItemIndex)
    {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, movies.get(clickedItemIndex));
        startActivity(intent);
    }

    @Override
    public void onTaskComplete(String result) throws JSONException
    {
        if(result!=null)
        {
            movies= MovieJsonUtils.getMoviesDataFromJson(result);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(movies,this,this);
            RecyclerView.LayoutManager  manager= new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }


}
