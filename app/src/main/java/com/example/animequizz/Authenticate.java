package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Authenticate extends AppCompatActivity {
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        Button button = (Button) findViewById(R.id.getUsername);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to another activity (list activity)
                username = (TextView) findViewById(R.id.username);
                Intent myIntent = new Intent(Authenticate.this, ListGenre.class);
                myIntent.putExtra( "username",username.getText().toString());
                Authenticate.this.startActivity(myIntent);
            }
        });


        }
}

