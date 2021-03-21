package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Result extends AppCompatActivity {
    // Attribut
    private String username;
    private int score;

    // Initializing info sended from the intent
    public void initInfo(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                username = null;

                score = 0;

            } else {
                score = extras.getInt("score");
                username = extras.getString("username");

            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize info
        initInfo(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager ();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Bundle arguments = new Bundle();
        arguments.putString("username", username);
        arguments.putString("score", String.valueOf(score));

        ResultFragment fm2 = new ResultFragment();
        fm2.setArguments(arguments);
        // Send the info to the fragment layout
        fragmentTransaction.replace(R.id.fragment, fm2, null);
        fragmentTransaction.commit();

    }

}
