package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Result extends AppCompatActivity {
    private String username;
    private int score;
    private int questionNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initInfo(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager ();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Bundle arguments = new Bundle();
        arguments.putString("username", username);
        arguments.putString("score", String.valueOf(score));
        arguments.putString("questionNb", String.valueOf(questionNb));

        ResultFragment fm2 = new ResultFragment();
        fm2.setArguments(arguments);

        fragmentTransaction.replace(R.id.fragment, fm2, null);
        fragmentTransaction.commit();

    }

    public void initInfo(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                username = null;
                questionNb = 0;
                score = 0;

            } else {
                score = extras.getInt("score");
                username = extras.getString("username");
                questionNb = extras.getInt("questionNb");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }
    }
}
