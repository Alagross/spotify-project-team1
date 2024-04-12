package com.example.spotifywrappeda1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExistingLogIn extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button loginButton;
    private TextView invalidText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existinglogin);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        invalidText = findViewById(R.id.invalidText);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if username and password are valid
                if (isValidUserCredentials()) {
                    // If valid, navigate to MyWrapped activity
                    navigateToMyWrappedActivity();
                } else {
                    // If invalid, display error message
                    invalidText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Method to check if username and password are valid (can be replaced with actual validation logic)
    private boolean isValidUserCredentials() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Add your validation logic here
        // For demo purposes, let's consider the credentials are valid if both fields are not empty
        return !username.isEmpty() && !password.isEmpty();
    }

    // Method to navigate to MyWrapped activity
    private void navigateToMyWrappedActivity() {
        // Create intent to navigate to MyWrapped activity
        // Replace MyWrappedActivity.class with the actual class name of your target activity
        Intent intent = new Intent(ExistingLogIn.this, myWrapped.class);
        startActivity(intent);
    }
}
