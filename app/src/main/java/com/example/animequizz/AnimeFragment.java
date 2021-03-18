package com.example.animequizz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AnimeFragment extends Fragment {

    TextView textView;

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
        textView = (TextView) view.findViewById(R.id.textView3);
        if (getArguments() != null) {
            String strtext = getArguments().getString("username");
            textView.setText(strtext);
        }

    }


}
