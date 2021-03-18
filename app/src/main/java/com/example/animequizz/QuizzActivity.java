package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizzActivity extends AppCompatActivity {

    private String username;
    private int genreID;
    private int score;
    private int questionNb;
    private List<Anime> animeList = new ArrayList<>();
    private int randomAnime;

    Button response_A,response_B,response_C,response_D;
    ImageView anime_image;
    TextView tvUsername,tvScore,tvQuestionNb;
    Intent quizzActivity;

    public void initInfo(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
                animeList = null;
                questionNb = 0;
                score = 0;
            } else {
                score = extras.getInt("score");
                username = extras.getString("username");
                genreID = extras.getInt("genreID");
                animeList = (List<Anime>) extras.getSerializable("animeList");
                questionNb = extras.getInt("questionNb");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
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

        tvUsername = (TextView) findViewById(R.id.username);
        tvScore = (TextView) findViewById(R.id.score);
        tvQuestionNb = (TextView) findViewById(R.id.questionNb);

        tvUsername.setText(username);
        tvScore.setText(String.valueOf("score:"+score));
        tvQuestionNb.setText("question : " +String.valueOf(questionNb));

        Randomizer();
        response_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(response_A.getText() == animeList.get(randomAnime).name){
                    score++;
                    questionNb++;
                    quizzActivity = getIntent();
                    quizzActivity.putExtra("score", score);
                    quizzActivity.putExtra("questionNb", questionNb);
                    finish();
                    startActivity(getIntent());
                }else{
                    questionNb++;
                    quizzActivity = getIntent();
                    quizzActivity.putExtra("score", score);
                    quizzActivity.putExtra("questionNb", questionNb);
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        response_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(response_B.getText() == animeList.get(randomAnime).name){
                    questionNb++;
                    score++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }else{
                    questionNb++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }

            }
        });
        response_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(response_C.getText() == animeList.get(randomAnime).name){
                    score++;
                    questionNb++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }else{
                    questionNb++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }
            }
        });
        response_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(response_D.getText() == animeList.get(randomAnime).name){
                    score++;
                    questionNb++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }else{
                    questionNb++;
                    quizzActivity = getIntent();
                    nextQuestion(quizzActivity,score,questionNb);
                }

            }
        });

    }

    public void Randomizer(){
        randomAnime = new Random().nextInt(animeList.size()-1);
        new AsyncBitmapDownloader(anime_image).execute(animeList.get(randomAnime).urlImage);

        List<Integer> listOfAnswer = Arrays.asList(randomAnime,new Random().nextInt(animeList.size()-1),new Random().nextInt(animeList.size()-1),new Random().nextInt(animeList.size()-1));
        Collections.shuffle(listOfAnswer);

        response_A.setText(animeList.get(listOfAnswer.get(0)).name);
        response_B.setText(animeList.get(listOfAnswer.get(1)).name);
        response_C.setText(animeList.get(listOfAnswer.get(2)).name);
        response_D.setText(animeList.get(listOfAnswer.get(3)).name);
    }

    public void nextQuestion(Intent actual,int score, int questionNb){

        //save score
        SharedPreferences preferences = getSharedPreferences("SP_" + username,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getInt("genre_"+genreID+"_lvl1_score",0)<score){
            editor.putInt("genre_"+genreID+"_lvl1_score",score);
            editor.commit();
        }


        quizzActivity.putExtra("score", score);
        quizzActivity.putExtra("questionNb", questionNb);
        finish();
        startActivity(getIntent());
    }
}
