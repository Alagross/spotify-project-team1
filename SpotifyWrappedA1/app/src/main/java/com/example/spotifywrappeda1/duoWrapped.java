package com.example.spotifywrappeda1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class duoWrapped extends AppCompatActivity {
    ImageButton settingsBtn;
    Button myWrappedBtn;
    Button exploreBtn;
    Button streamBtn;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duo_wrapped);

        settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(duoWrapped.this,Settings.class);
                        startActivity(i);
                    }
                }
        );
        myWrappedBtn = findViewById(R.id.myWrappedButton);
        myWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(duoWrapped.this, myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        exploreBtn = findViewById(R.id.exploreButton);
        exploreBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(duoWrapped.this, Explore.class);
                        startActivity(i);
                    }
                }
        );
        streamBtn = findViewById(R.id.streamButton);
        streamBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(duoWrapped.this, Stream.class);
                        startActivity(i);
                    }
                }
        );

    }
}
