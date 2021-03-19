package com.example.animequizz;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncAnimeJSONDataForListGenre extends AsyncTask<String,Void, JSONObject> {
    private ListGenre.MyAdapter adapter;
    private int curr_page;
    private static final int NB_GENRE = 10;
    private static List<String> urlList = new ArrayList<>();
    private List<PresentationGenre> genreList = new ArrayList<>();

    public AsyncAnimeJSONDataForListGenre(ListGenre.MyAdapter adapter, int curr_page) {
        this.adapter = adapter;
        this.curr_page = curr_page;
    }

    @Override
    protected JSONObject doInBackground(String... params){

        //url with Json format
        String stringUrl;
        JSONObject jsonObject = null;
        stringUrl = params[0]+curr_page+"/1";
        Log.i("item 0:", stringUrl);
        jsonObject = HttpConnection(stringUrl);
        return jsonObject;
    }
    protected void onPostExecute(JSONObject jsonObject) {

        String stringUrl = "https://api.jikan.moe/v3/genre/anime/";

        if (jsonObject != null) {
            try {
                int index = 0;
                String urlImage = jsonObject.getJSONArray("anime").getJSONObject(index).getString("image_url");
                String genreName = jsonObject.getJSONObject("mal_url").getString("name");
                Log.i("item 0:", urlImage);
                Log.i("item 0:", genreName);
                //find an unique image to avoid doublon
                while (urlList.contains(urlImage)){
                    index++;
                    urlImage = jsonObject.getJSONArray("anime").getJSONObject(index).getString("image_url");
                }
                urlList.add(urlImage);

                adapter.dd(new PresentationGenre(genreName,urlImage,curr_page));
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            curr_page--;
            Log.e("Error", "Error: jsonObject is null");
        }
        if (curr_page != NB_GENRE){
            new AsyncAnimeJSONDataForListGenre(adapter,curr_page = curr_page+1).execute(stringUrl);
        }
    }
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
    private JSONObject HttpConnection(String stringUrl){
        JSONObject jsonObject = null;
        try {
            URL myUrl = new URL(stringUrl);
            //opens the connection to a specified URL
            HttpURLConnection URLConnection = (HttpURLConnection) myUrl.openConnection();
            try {
                //BufferedInputStream allow us to read a stream of data through a buffer
                InputStream in = new BufferedInputStream(URLConnection.getInputStream());
                //get the JsonObject
                String s = readStream(in);
                jsonObject = new JSONObject(s);

            }finally {
                URLConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
