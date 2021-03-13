package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        String username;
        int difficulty;
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
