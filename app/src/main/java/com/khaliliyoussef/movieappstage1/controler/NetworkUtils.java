
package com.khaliliyoussef.movieappstage1.controler;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class NetworkUtils {

//TODO this code is slower that the other by small quanteties but it's clean and simple
//    public static String getResponseFromHttpUrl(URL url)throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder().url(url).build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
    //TODO this code is much faster
public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}