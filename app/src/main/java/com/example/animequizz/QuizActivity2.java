package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity2 extends AppCompatActivity {
    // Attribut
    private String username;
    private int genreID;
    private int score;
    private int questionNb;
    private List<Anime> animeList = new ArrayList<>();
    private List<Anime> fourAnime = new ArrayList<>();

    // Layout item
    private TextView tvUsername, tvScore, tvQuestionNb;
    private ImageView anime_Image_A, anime_Image_B, anime_Image_C, anime_Image_D;
    private Button validation;
    private Spinner snipper_A, snipper_B, snipper_C, snipper_D;


    // Initializing info sended from the intent
    public void initInfo(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                username = null;
                animeList = null;
                questionNb = 0;
                score = 0;
            } else {
                score = extras.getInt("score");
                username = extras.getString("username");
                animeList = (List<Anime>) extras.getSerializable("animeList");
                questionNb = extras.getInt("questionNb");
                genreID = extras.getInt("genreID");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
            animeList = (List<Anime>) savedInstanceState.getSerializable("animeList");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        // initialize info
        initInfo(savedInstanceState);

        // Set to var to the item of the layout
        tvUsername = (TextView) findViewById(R.id.username);
        tvScore = (TextView) findViewById(R.id.score);
        tvQuestionNb = (TextView) findViewById(R.id.questionNb);

        tvUsername.setText(username);
        tvScore.setText(String.valueOf("score:" + score));
        tvQuestionNb.setText("question : " + String.valueOf(questionNb));

        snipper_A = (Spinner) findViewById(R.id.snipper_A);
        snipper_B = (Spinner) findViewById(R.id.snipper_B);
        snipper_C = (Spinner) findViewById(R.id.snipper_C);
        snipper_D = (Spinner) findViewById(R.id.snipper_D);

        anime_Image_A = (ImageView) findViewById(R.id.anime_Image_A);
        anime_Image_B = (ImageView) findViewById(R.id.anime_Image_B);
        anime_Image_C = (ImageView) findViewById(R.id.anime_Image_C);
        anime_Image_D = (ImageView) findViewById(R.id.anime_Image_D);

        validation = (Button) findViewById(R.id.validation);

        // get 4 random anime from the list
        fourAnime = randomAnime(animeList);


        // Layout for one ROW of Spinner
        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_A,0));
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_B,1));
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_C,2));

        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_D,3));

        // Layout for All ROWs of Spinner.
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.snipper_A.setAdapter(adapterA);
        this.snipper_B.setAdapter(adapterB);
        this.snipper_C.setAdapter(adapterC);
        this.snipper_D.setAdapter(adapterD);

        // When user select a List-Item.
        this.snipper_A.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.snipper_B.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.snipper_C.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.snipper_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Validation of the answer
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if all 4 answers is correct
                if(snipper_A.getSelectedItem().toString() == fourAnime.get(0).name &&
                        snipper_B.getSelectedItem().toString() == fourAnime.get(1).name &&
                        snipper_C.getSelectedItem().toString() == fourAnime.get(2).name &&
                        snipper_D.getSelectedItem().toString() == fourAnime.get(3).name){
                    score++;
                }
                questionNb++;


                //save score
                SharedPreferences preferences = getSharedPreferences("SP_" + username,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (preferences.getInt("genre_"+genreID+"_lvl2_score",0)<score){
                    editor.putInt("genre_"+genreID+"_lvl2_score",score);
                    editor.commit();
                }

                Intent quizActivity2 = getIntent();
                quizActivity2.putExtra("score", score);
                quizActivity2.putExtra("questionNb", questionNb);
                finish();
                startActivity(quizActivity2);

            }
        });

        // End of the quizz
        if(questionNb >10){
            Intent myIntent = new Intent(QuizActivity2.this, Result.class);
            myIntent.putExtra("username",username);
            myIntent.putExtra("score", score);
            myIntent.putExtra("questionNb", questionNb);
            finish();
            QuizActivity2.this.startActivity(myIntent);
        }

    }

    private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    // Get Random anime for the quizz and a set of answer in a spinner
    public List<String> Randomizer(ImageView anime_Image,int number) {
        List<String> spinner = new ArrayList<>();

        new AsyncBitmapDownloader(anime_Image).execute(fourAnime.get(number).urlImage);

        List<Integer> listOfAnswer = Arrays.asList(0,1,2,3);
        // shuffle the list
        Collections.shuffle(listOfAnswer);

        spinner.add(fourAnime.get(listOfAnswer.get(0)).name);
        spinner.add(fourAnime.get(listOfAnswer.get(1)).name);
        spinner.add(fourAnime.get(listOfAnswer.get(2)).name);
        spinner.add(fourAnime.get(listOfAnswer.get(3)).name);

        return spinner;
    }

    // get random anime
    public List<Anime> randomAnime(List<Anime> animelist) {
        List<Anime> listAnswer = new ArrayList<>();
        if (animelist != null) {
            for (int i = 0; i < 4; i++) {
                int randomAnime = new Random().nextInt(animelist.size()-1);
                listAnswer.add(new Anime(animelist.get(randomAnime).name, animelist.get(randomAnime).urlImage));
            }
        }
        return listAnswer;
    }






}
