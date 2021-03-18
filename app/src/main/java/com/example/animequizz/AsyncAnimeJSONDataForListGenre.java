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

public class AsyncAnimeJSONDataForListGenre extends AsyncTask<String,Void, List<PresentationGenre>> {
    private ListGenre.MyAdapter adapter;
    private static final int NB_GENRE = 5;

    public AsyncAnimeJSONDataForListGenre(ListGenre.MyAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<PresentationGenre> doInBackground(String... params){
        List<PresentationGenre> genreList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        //url with Json format
        String stringUrl;
        JSONObject jsonObject = null;

        for (int i=1;i<NB_GENRE+1;i++) {

            stringUrl = params[0]+i+"/1";
            Log.i("item 0:", stringUrl);
            jsonObject = HttpConnection(stringUrl);
            if (jsonObject != null) {
                try {
                    int index = 0;
                    String urlImage = jsonObject.getJSONArray("anime").getJSONObject(index).getString("image_url");
                    String genreName = jsonObject.getJSONObject("mal_url").getString("name");
                    Log.i("item 0:", urlImage);
                    Log.i("item 0:", genreName);
                    while (urlList.contains(urlImage)){
                        index++;
                        urlImage = jsonObject.getJSONArray("anime").getJSONObject(index).getString("image_url");
                    }
                    urlList.add(urlImage);
                    genreList.add(new PresentationGenre(genreName,urlImage,i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                i--;
                Log.e("Error", "Error: jsonObject is null");
            }
        }
        return genreList;
    }
    protected void onPostExecute(List<PresentationGenre> result) {

        if (result != null){
            for (int i = 0; i < result.size(); i++) {
                adapter.dd(result.get(i));
            }
            //set notifyDataSetChanged to the adapter
            adapter.notifyDataSetChanged();
        }else{
            Log.e("Error", "Error: jsonObject is null");
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
