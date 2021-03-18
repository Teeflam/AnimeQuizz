package com.example.animequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Authenticate extends AppCompatActivity {
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Authenticate.this);
        final SharedPreferences.Editor editor = preferences.edit();
        EditText et =  findViewById(R.id.username);
        Button button = (Button) findViewById(R.id.getUsername);
        et.setText(preferences.getString("last_user", "Anonyme"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences1 = getSharedPreferences("SP_" + username,MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                //go to another activity (list activity)
                username = (TextView) findViewById(R.id.username);
                Intent myIntent = new Intent(Authenticate.this, ListGenre.class);
                editor1.putString("username", username.getText().toString());
                editor.putString("last_user", username.getText().toString());
                editor.commit();

                myIntent.putExtra( "username",username.getText().toString());
                Authenticate.this.startActivity(myIntent);
            }
        });


        }
}

