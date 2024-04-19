package com.example.spotifywrappeda1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrappeda1.R;
import com.example.spotifywrappeda1.Settings;
import com.example.spotifywrappeda1.myWrapped;

public class EditAccount extends AppCompatActivity {

    EditText newUsernameEditText, newPasswordEditText;
    Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        //Adds functionality to back button
        ImageButton changeBackBtn;
        changeBackBtn = findViewById(R.id.changeBackButton);
        changeBackBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(EditAccount.this, Settings.class);
                        startActivity(i);
                    }
                }
        );

        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        changeBtn = findViewById(R.id.changeBtn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newUsernameEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                // Check if fields are empty
                if (newUsername.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(EditAccount.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform desired action with newUsername and newPassword
                    // For example, update the user's account information
                    // You can add your logic here
                    // For demonstration purpose, displaying a toast message
                    Toast.makeText(EditAccount.this, "Username: " + newUsername + "\nPassword: " + newPassword, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
