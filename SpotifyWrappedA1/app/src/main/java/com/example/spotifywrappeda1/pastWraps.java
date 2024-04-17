package com.example.spotifywrappeda1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class pastWraps extends AppCompatActivity {
    ImageButton settingsBtn;
    Button myWrappedBtn;
    Button personalityBtn;
    Button recommendBtn;
    Button gameBtn;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_wraps);

        settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(pastWraps.this,Settings.class);
                        startActivity(i);
                    }
                }
        );
        myWrappedBtn = findViewById(R.id.myWrappedButton);
        myWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(pastWraps.this, myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        personalityBtn = findViewById(R.id.personalityButton);
        personalityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(pastWraps.this, Personality.class);
                        startActivity(i);
                    }
                }
        );
        recommendBtn = findViewById(R.id.recommendButton);
        recommendBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(pastWraps.this, Recommend.class);
                        startActivity(i);
                    }
                }
        );
        gameBtn = findViewById(R.id.gameButton);
        gameBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(pastWraps.this, Game.class);
                        startActivity(i);
                    }
                }
        );

    }
}
