package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class myWrapped extends AppCompatActivity {
    ImageButton settingsBtn;
    Button duoWrappedBtn;
    Button exploreBtn;
    Button streamBtn;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wrapped);

        settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this,Settings.class);
                        startActivity(i);
                    }
                }
        );
        duoWrappedBtn = findViewById(R.id.duoWrappedButton);
        duoWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, duoWrapped.class);
                        startActivity(i);
                    }
                }
        );
        exploreBtn = findViewById(R.id.exploreButton);
        exploreBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, Explore.class);
                        startActivity(i);
                    }
                }
        );
        streamBtn = findViewById(R.id.streamButton);
        streamBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, Stream.class);
                        startActivity(i);
                    }
                }
        );

    }
}
