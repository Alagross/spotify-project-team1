package com.example.spotifywrappeda1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        // Find the button by its ID
        Button goToExistingLogInButton = findViewById(R.id.first_login_button);

        // Set a click listener for the button
        goToExistingLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent to navigate to the SecondPage activity
                Intent intent = new Intent(FirstPage.this, ExistingLogIn.class);

                // Start the SecondPage activity
                startActivity(intent);
            }
        });

        // Find the button by its ID
        Button goToExistingSignUpButton = findViewById(R.id.signup_button);

        // Set a click listener for the button
        goToExistingSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent to navigate to the SecondPage activity
                Intent intent = new Intent(FirstPage.this, Login.class);

                // Start the SecondPage activity
                startActivity(intent);
            }
        });
    }
}
