package com.example.spotifywrappeda1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
public class Game extends AppCompatActivity {
    ImageButton settingsBtn;
    Button myWrappedBtn;
    Button pastWrapsBtn;
    Button personalityBtn;
    Button recommendBtn;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        myWrappedBtn = findViewById(R.id.myWrappedButton);
        myWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Game.this, myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        pastWrapsBtn = findViewById(R.id.pastWrapsButton);
        pastWrapsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Game.this, pastWraps.class);
                        startActivity(i);
                    }
                }
        );
        personalityBtn = findViewById(R.id.personalityButton);
        personalityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Game.this, Personality.class);
                        startActivity(i);
                    }
                }
        );
        recommendBtn = findViewById(R.id.recommendButton);
        recommendBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Game.this, Recommend.class);
                        startActivity(i);
                    }
                }
        );
    }
}
