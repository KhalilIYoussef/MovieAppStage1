package com.khaliliyoussef.movieappstage1.interfaces;

import org.json.JSONException;


public interface AsyncTaskCompleteListener
{
    public void onTaskComplete(String result) throws JSONException;
}
