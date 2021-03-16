package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity2 extends AppCompatActivity {

    private String username;
    private int difficulty;
    private List<Anime> animeList = new ArrayList<>();
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        initInfo(savedInstanceState);
    }
    public void initInfo(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                difficulty = 0;
                animeList = null;
            } else {
                username= extras.getString("username");
                difficulty = extras.getInt("difficulty");
                score = extras.getInt("score");
                animeList = (List<Anime>) extras.getSerializable("animeList");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
            difficulty = (int) savedInstanceState.getSerializable("difficulty");
            animeList = (List<Anime>) savedInstanceState.getSerializable("animeList");
        }
    }

}
