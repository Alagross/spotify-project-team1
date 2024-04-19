package com.example.spotifywrappeda1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class editAccount  extends AppCompatActivity {
    Button confirmBtn;
    Button cancelBtn;
    TextInputEditText newEmailInput;
    TextInputEditText newPasswordInput;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        cancelBtn = findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(editAccount.this, Settings.class);
                        startActivity(i);
                    }
                }
        );

        confirmBtn = findViewById(R.id.confirmButton);
        confirmBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean update = false;
                        newEmailInput = findViewById(R.id.emailTextInput);
                        newPasswordInput = findViewById(R.id.passwordTextInput);
                        String newEmail = String.valueOf(newEmailInput.getText());
                        String newPassword = String.valueOf(newPasswordInput.getText());
                        if (newEmail != null) {
                            updateEmailAddress(newEmail);
                            update = true;
                        }
                        if (newPassword != null && newPassword.length() >= 6) {
                            updatePassword(newPassword);
                            update = true;
                        }
                        if (update) {
                            Intent i = new Intent(editAccount.this, Settings.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(editAccount.this, "New Email and/or Password Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
        );
    }

    private void updateEmailAddress(String newEmail) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.updateEmail(newEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Email address updated successfully
                            // Inform the user and update UI accordingly
                            Toast.makeText(this, "Email address updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Email address update failed
                            // Handle error and inform the user
                            Toast.makeText(this, "Failed to update email address: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void updatePassword(String newPassword) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Password updated successfully
                            // Inform the user and update UI accordingly
                            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Password update failed
                            // Handle error and inform the user
                            Toast.makeText(this, "Failed to update password: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
