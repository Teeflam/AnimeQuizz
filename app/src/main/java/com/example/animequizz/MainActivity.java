package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String username;
    private String genreName;
    private int genreID;
    private TextView header;
    private static List<Anime> animeList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (TextView) findViewById(R.id.header);
        Button button_d1 = (Button) findViewById(R.id.stage_1);
        Button button_d2 = (Button) findViewById(R.id.stage_2);

        initInfo(savedInstanceState);
        header.setText("Welcome " + username + ", to the Anime Quiz Game" );

        //random 1 to 2
        int randomeEasy = new Random().nextInt(3)+1;
        // start the async Anime list fetching
        new AsyncAnimeJSONDataForList(animeList,1,3,genreID).execute("https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&genre="+genreID+"&page=");

        // access to new activity quiz with the difficulty level
        button_d1.setOnClickListener(new View.OnClickListener() {
            @Override   
            public void onClick(View v) {
                if (animeList.size()!=0){
                    //create an intent to change activity
                    Intent myIntent = new Intent(MainActivity.this, QuizzActivity.class);
                    //put extras informations about username and level
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("score", 0);
                    myIntent.putExtra("questionNb", 1);
                    myIntent.putExtra("animeList", (Serializable) animeList);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });

        button_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animeList.size()!=0){
                    //create an intent to change activity
                    Intent myIntent = new Intent(MainActivity.this, QuizActivity2.class);
                    //put extras informations about username and level
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("score", 0);
                    myIntent.putExtra("questionNb", 1);
                    myIntent.putExtra("animeList", (Serializable) animeList);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });
    }
    public static void setAnimeList(List<Anime> animeList){
        MainActivity.animeList = animeList;
    }
    public void initInfo(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                genreName = null;
                genreID = 0;
            } else {
                username= extras.getString("username");
                genreName= extras.getString("genre");
                genreID= extras.getInt("genreID");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
            genreName = (String) savedInstanceState.getSerializable("genre");
            genreID = (int) savedInstanceState.getSerializable("genreID");

        }
    }
}
