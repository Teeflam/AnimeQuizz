package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizzActivity extends AppCompatActivity {

    private String username;
    private int difficulty;
    private int score;
    private int questionNb;
    private List<Anime> animeList = new ArrayList<>();
    Button response_A,response_B,response_C,response_D;
    ImageView anime_image;

    public void initInfo(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                difficulty = 0;
                animeList = null;
                questionNb = 0;
                score = 0;
            } else {
                score = extras.getInt("score");
                username= extras.getString("username");
                difficulty = extras.getInt("difficulty");
                animeList = (List<Anime>) extras.getSerializable("animeList");
                questionNb = extras.getInt("questionNb");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
            difficulty = (int) savedInstanceState.getSerializable("difficulty");
            animeList = (List<Anime>) savedInstanceState.getSerializable("animeList");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        // init
        response_A = (Button) findViewById(R.id.response_A);
        response_B = (Button) findViewById(R.id.response_B);
        response_C = (Button) findViewById(R.id.response_C);
        response_D = (Button) findViewById(R.id.response_D);
        anime_image = (ImageView) findViewById(R.id.anime_image);

        initInfo(savedInstanceState);

        TextView tvUsername = (TextView) findViewById(R.id.username);
        TextView tvScore = (TextView) findViewById(R.id.score);
        TextView tvQuestionNb = (TextView) findViewById(R.id.questionNb);

        tvUsername.setText(username);
        tvScore.setText(String.valueOf(score));
        tvQuestionNb.setText(String.valueOf(questionNb));


        int randomAnime = new Random().nextInt(49);
        new AsyncBitmapDownloader(anime_image).execute(animeList.get(randomAnime).urlImage);

       // List<Integer> listOfAnswer = List.(randomAnime,new Random().nextInt(49),new Random().nextInt(49),new Random().nextInt(49));
        response_A.setText(animeList.get(randomAnime).name);
        response_B.setText(animeList.get(new Random().nextInt(49)).name);
        response_C.setText(animeList.get(new Random().nextInt(49)).name);
        response_D.setText(animeList.get(new Random().nextInt(49)).name);
    }
}
