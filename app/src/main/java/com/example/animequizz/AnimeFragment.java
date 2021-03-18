package com.example.animequizz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnimeFragment extends Fragment {

    TextView tvUsername;
    TextView tvScore;
    TextView tvQuestionNb;
    Button button;

    public AnimeFragment() {
       super(R.layout.fragment_anime);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        tvUsername = (TextView) view.findViewById(R.id.fragmentUsername);
        tvScore= (TextView) view.findViewById(R.id.fragmentScore);
        button = (Button) view.findViewById(R.id.back_to_menu);
        if (getArguments() != null) {
            tvUsername.setText(getArguments().getString("username"));
            tvScore.setText(getArguments().getString("score"));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });
    }
    public void backToMenu() {
        Intent intent = new Intent(getActivity(), ListGenre.class);
        intent.putExtra("username",tvUsername.getText().toString());
        startActivity(intent);
    }
}



