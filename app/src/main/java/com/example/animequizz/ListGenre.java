package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;

public class ListGenre extends AppCompatActivity {
    // attributes
    private String username;
    private MyAdapter adapter;
    // layout items
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_genre);
        // init value
        String url;
        lv = (ListView) findViewById(R.id.listGenre);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        url = "https://api.jikan.moe/v3/genre/anime/";
        initInfo(savedInstanceState);
        // create new async task to fill list view
        AsyncAnimeJSONDataForListGenre asyncTaskList = new AsyncAnimeJSONDataForListGenre(adapter,1);
        asyncTaskList.execute(url);
    }
    public class MyAdapter extends BaseAdapter {
        //a vector that store all url
        Vector<PresentationGenre> vector = new Vector<>();

        //return the number of url in vector
        @Override
        public int getCount() {
            return vector.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if( convertView == null ){
                //We create a View:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.bitmap_layout, parent, false);
            }
            // set button content
            Button button = (Button) convertView.findViewById(R.id.button);
            final String genre = vector.get(position).getGenreName();
            final int genreID = vector.get(position).getGenreNum();
            button.setText(genre);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // put extra info and launch new activity
                    Intent myIntent = new Intent(ListGenre.this, DifficultyChoice.class);
                    myIntent.putExtra( "username",username);
                    myIntent.putExtra( "genre",genre);
                    myIntent.putExtra( "genreID",genreID);
                    ListGenre.this.startActivity(myIntent);
                }
            });
            // set image view content
            final ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            ImageRequest ir = new ImageRequest(vector.get(position).getImageUrl(), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    iv.setImageBitmap(response);

                }

            },0,0, ImageView.ScaleType.CENTER_CROP,null, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ListGenre.this,"Some Thing Goes Wrong", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });
            MySingleton.getInstance(parent.getContext()).addToRequestQueue(ir);

            return convertView;
        }
        // add a new element in vector
        public void dd(PresentationGenre pg){
            vector.add(pg);
        }
    }
    // init value from intent
    public void initInfo(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
            } else {
                username= extras.getString("username");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }
    }
}
