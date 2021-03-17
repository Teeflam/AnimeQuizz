package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.concurrent.TimeUnit;

public class QuizActivity2 extends AppCompatActivity {

    private String username;
    private int difficulty;
    private int score;
    private int questionNb;
    private List<Anime> animeList = new ArrayList<>();
    private List<String> a = new ArrayList<>();
    private TextView tvUsername,tvScore,tvQuestionNb;
    private ImageView anime_Image_A,anime_Image_B,anime_Image_C,anime_Image_D;
    private Button validation;

    private Intent quizActivity2;
    private Spinner snipper_A,snipper_B,snipper_C,snipper_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        initInfo(savedInstanceState);

        tvUsername = (TextView) findViewById(R.id.username);
        tvScore = (TextView) findViewById(R.id.score);
        tvQuestionNb = (TextView) findViewById(R.id.questionNb);

        tvUsername.setText(username);
        tvScore.setText(String.valueOf("score:"+score));
        tvQuestionNb.setText("question : " +String.valueOf(questionNb));

        snipper_A = (Spinner) findViewById(R.id.snipper_A);
        snipper_B = (Spinner) findViewById(R.id.snipper_B);
        snipper_C = (Spinner) findViewById(R.id.snipper_C);
        snipper_D = (Spinner) findViewById(R.id.snipper_D);

        anime_Image_A = (ImageView) findViewById(R.id.anime_Image_A);
        anime_Image_B = (ImageView) findViewById(R.id.anime_Image_B);
        anime_Image_C = (ImageView) findViewById(R.id.anime_Image_C);
        anime_Image_D = (ImageView) findViewById(R.id.anime_Image_D);

        validation = (Button) findViewById(R.id.validation);

        // (@resource) android.R.layout.simple_spinner_item:
        //   The resource ID for a layout file containing a TextView to use when instantiating views.
        //    (Layout for one ROW of Spinner)
        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_A));
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_B));
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_C));

        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                Randomizer(anime_Image_D));

        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
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


        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(snipper_A ===)
                quizActivity2 = getIntent();
                quizActivity2.putExtra("score", score);
                quizActivity2.putExtra("questionNb", questionNb);
                finish();
                startActivity(quizActivity2);

            }
        });


    }


    private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
        String item  = parent.getItemAtPosition(position).toString();
    }



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
    public List<String> Randomizer(ImageView anime_Image){
        List<String> spinner= new ArrayList<>();
        int randomAnime = new Random().nextInt(49);
        new AsyncBitmapDownloader( anime_Image).execute(animeList.get(randomAnime).urlImage);

        List<Integer> listOfAnswer = Arrays.asList(randomAnime,new Random().nextInt(49),new Random().nextInt(49),new Random().nextInt(49));
        Collections.shuffle(listOfAnswer);

        spinner.add(animeList.get(listOfAnswer.get(0)).name);
        spinner.add(animeList.get(listOfAnswer.get(1)).name);
        spinner.add(animeList.get(listOfAnswer.get(2)).name);
        spinner.add(animeList.get(listOfAnswer.get(3)).name);

        return spinner;
    }



}
