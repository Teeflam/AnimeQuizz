package com.example.animequizz;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AsyncAnimeJSONDataForList extends AsyncTask<String, Void, JSONObject> {
    private List<Anime> animeList = new ArrayList<>();
    private int curr_page;
    private int num_page;
    private int genreID;

    public AsyncAnimeJSONDataForList(List<Anime> animeList, int curr_page, int num_page, int genreID) {
        this.animeList = animeList;
        this.curr_page = curr_page;
        this.num_page = num_page;
        this.genreID = genreID;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject result = null;

        try {
            // retrieve the url
            URL url = new URL(params[0]+curr_page);

            // connect to the url
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            try {
                // get data
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(is);

                // transform it into a JSONObject
                result = new JSONObject(s);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(JSONObject result) {

        try {
            // retrieve the JSON array of items
            JSONArray items = result.getJSONArray("results");

            // Loop all items
            for (int i = 0; i < items.length(); i++) {
                // retrieve the image url
                String name = items.getJSONObject(i).getString("title");
                String imageUrl = items.getJSONObject(i).getString("image_url");
                animeList.add(new Anime(name, imageUrl));
            }
            DifficultyChoice.setAnimeList(animeList);
            if (curr_page != num_page){
                new AsyncAnimeJSONDataForList(animeList,curr_page = curr_page+1,num_page,genreID).execute("https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&genre="+genreID+"&page=");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            // initialize the output stream
            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            // retrieve the output content
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }

            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
