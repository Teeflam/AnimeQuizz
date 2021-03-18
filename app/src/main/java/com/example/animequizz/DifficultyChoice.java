package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DifficultyChoice extends AppCompatActivity {
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
                    Intent myIntent = new Intent(DifficultyChoice.this, QuizActivity.class);
                    //put extras informations about username and level
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("genreID", genreID);
                    myIntent.putExtra("score", 0);
                    myIntent.putExtra("questionNb", 1);
                    myIntent.putExtra("animeList", (Serializable) animeList);
                    DifficultyChoice.this.startActivity(myIntent);
                }
            }
        });

        button_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animeList.size()!=0){
                    //create an intent to change activity
                    Intent myIntent = new Intent(DifficultyChoice.this, QuizActivity2.class);
                    //put extras informations about username and level
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("genreID", genreID);
                    myIntent.putExtra("score", 0);
                    myIntent.putExtra("questionNb", 1);
                    myIntent.putExtra("animeList", (Serializable) animeList);
                    DifficultyChoice.this.startActivity(myIntent);
                }
            }
        });
    }
    public static void setAnimeList(List<Anime> animeList){
        DifficultyChoice.animeList = animeList;
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
        SharedPreferences preferences = getSharedPreferences("SP_" + username,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int score1 = preferences.getInt("genre_"+genreID+"_lvl1_score",0);
        int score2 = preferences.getInt("genre_"+genreID+"_lvl2_score",0);
        if(score1 != 0){
            TextView scoreLvl1 = findViewById(R.id.score_lvl1);
            scoreLvl1.setText("RECORD : "+score1);
        }
        if(score2 != 0){
            TextView scoreLvl2 = findViewById(R.id.score_lvl2);
            scoreLvl2.setText("RECORD : "+score2);
        }
    }
}
