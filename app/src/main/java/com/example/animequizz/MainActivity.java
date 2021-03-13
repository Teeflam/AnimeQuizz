package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String username;
    private TextView header;
    private static List<Anime> animeList = new ArrayList<>();


    public static void setAnimeList(List<Anime> animeList){
        MainActivity.animeList = animeList;
        Log.i("test",MainActivity.animeList.get(0).name);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (TextView) findViewById(R.id.header);
        Button button_d1 = (Button) findViewById(R.id.stage_1);
        Button button_d2 = (Button) findViewById(R.id.stage_2);

        //get the extra information sended through intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username= null;
            } else {
                username= extras.getString("username");
            }
        } else {
            username= (String) savedInstanceState.getSerializable("username");
        }
        header.setText("Welcome " + username + ", to the Anime Quiz Game" );



        // access to new activity quiz with the difficulty level
        button_d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start the async Anime list fetching
                try {
                    new AsyncAnimeJSONDataForList().execute("https://api.jikan.moe/v3/top/anime/1").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                    //create an intent to change activity
                    Intent myIntent = new Intent(MainActivity.this, QuizzActivity.class);
                    //put extras informations about username and level
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("difficulty", 1);

                   // myIntent.putExtra("animeList", (Serializable) animeList);




                MainActivity.this.startActivity(myIntent);
            }
        });

        button_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start the async Anime list fetching
                new AsyncAnimeJSONDataForList().execute("https://api.jikan.moe/v3/top/anime/1");

                //go to another activity (list activity)
                Intent myIntent = new Intent(MainActivity.this, QuizzActivity.class);
                myIntent.putExtra( "username",username);
                myIntent.putExtra( "difficulty",2);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
