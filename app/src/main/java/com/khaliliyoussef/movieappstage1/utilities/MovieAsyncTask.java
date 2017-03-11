package com.khaliliyoussef.movieappstage1.utilities;

import android.net.Uri;
import android.os.AsyncTask;

import com.khaliliyoussef.movieappstage1.interfaces.AsyncTaskCompleteListener;

import org.json.JSONException;

import java.net.URL;


public class MovieAsyncTask extends AsyncTask<Void,Void,String> {

    private AsyncTaskCompleteListener callback;
    private String url;

    public MovieAsyncTask(AsyncTaskCompleteListener callback, String url) {
        this.callback = callback;
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String movieJSN=null;
        if(url!=null)
        {
            try {
                Uri uri = Uri.parse(url).buildUpon().build();
                return NetworkUtils.getResponseFromHttpUrl(new URL(uri.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
	    if(s != null)
            callback.onTaskComplete(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
