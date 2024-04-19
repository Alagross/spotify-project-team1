package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {
    ImageButton backBtn;
    Button darkModeBtn;
    static boolean darkMode = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        //Back Button functionality
        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Settings.this,myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        // Dark Mode Button: switches the theme for the app when clicked
        darkModeBtn = findViewById(R.id.darkModeButton);
        darkModeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!(darkMode)) {
                            darkMode = true;
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        } else {
                            darkMode = false;
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
                    }
                }
        );
        //Edit Account button functionality
        Button editAccountButton = findViewById(R.id.editAccountButton);
        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, EditAccount.class);
                startActivity(intent);
            }
        });
    }
}
