package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {
    public  List<Anime> animeList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        String username;
        int difficulty;

        // start the async Anime list fetching
        new AsyncAnimeJSONDataForList().execute("https://api.jikan.moe/v3/top/anime/1");

        //get the extra information sended through intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                difficulty = 0;
            } else {
                username= extras.getString("username");
                difficulty = extras.getInt("difficulty");
            }
        } else {
            username= (String) savedInstanceState.getSerializable("username");
        }

    }
}
