package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {
    public  List<Anime> animeList =  new ArrayList<>();

    Button response_A,response_B,response_C,response_D;
    ImageView anime_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        String username;
        int difficulty;
         List<Anime> animeList = new ArrayList<>();

        response_A = (Button) findViewById(R.id.response_A);
        response_B = (Button) findViewById(R.id.response_B);
        response_C = (Button) findViewById(R.id.response_C);
        response_D = (Button) findViewById(R.id.response_D);
        anime_image = (ImageView) findViewById(R.id.anime_image);


        //get the extra information sended through intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                difficulty = 0;
            } else {
                username= extras.getString("username");
                difficulty = extras.getInt("difficulty");

                animeList = (List<Anime>) extras.getSerializable("animeList");

            }
        } else {
            username= (String) savedInstanceState.getSerializable("username");
        }

        for(int i = 0; i < 10 ; i++){
            Log.i("test",animeList.get(i).name);
        }


    }
}
