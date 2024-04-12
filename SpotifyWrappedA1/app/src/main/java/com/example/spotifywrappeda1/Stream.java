package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
public class Stream extends AppCompatActivity {
    ImageButton settingsBtn;
    Button myWrappedBtn;
    Button duoWrappedBtn;
    Button exploreBtn;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream);

        settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Stream.this,Settings.class);
                        startActivity(i);
                    }
                }
        );
        myWrappedBtn = findViewById(R.id.myWrappedButton);
        myWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Stream.this, myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        duoWrappedBtn = findViewById(R.id.duoWrappedButton);
        duoWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Stream.this, duoWrapped.class);
                        startActivity(i);
                    }
                }
        );
        exploreBtn = findViewById(R.id.exploreButton);
        exploreBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Stream.this, Explore.class);
                        startActivity(i);
                    }
                }
        );

    }
}
