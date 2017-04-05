package com.khaliliyoussef.movieappstage1.controler;
import android.net.Uri;
import android.os.AsyncTask;
import com.khaliliyoussef.movieappstage1.interfaces.AsyncTaskCompleteListener;
import org.json.JSONException;
import java.net.URL;


public class MovieAsyncTask extends AsyncTask<String,Void,String> {

    private AsyncTaskCompleteListener callback;

    public MovieAsyncTask(AsyncTaskCompleteListener callback)
    {
        this.callback = callback;

    }

    @Override
    protected String doInBackground(String... urls)
    {

        if(urls!=null)
        {
            try {
                Uri uri = Uri.parse(urls[0]).buildUpon().build();
                return NetworkUtils.getResponseFromHttpUrl(new URL(uri.toString()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        try {
	    if(s != null)
	    {
            callback.onTaskComplete(s);
        }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
