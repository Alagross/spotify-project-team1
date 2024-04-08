package com.example.spotifywrappeda1;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
// Make sure to change as necessary when implementing Firebase and Spotify API
        Button loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), myWrapped.class);
                        startActivity(i);
                        finish();
                    }
                }
        );
    }
}
