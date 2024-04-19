package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
    ImageButton backBtn;
    Button darkModeBtn;
    Button signoutBtn;
    Button deleteAccountBtn;
    Button editAccountBtn;
    static boolean darkMode = false;
    static boolean deleteCheck = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

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
        signoutBtn = findViewById(R.id.signoutButton);
        signoutBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(Settings.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );
        deleteAccountBtn = findViewById(R.id.deleteAccountButton);
        deleteAccountBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (deleteCheck) {
                            FirebaseAuth.getInstance().getCurrentUser().delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(Settings.this,MainActivity.class);
                                                startActivity(i);
                                            } else {
                                                Toast.makeText(Settings.this, "Account Failed to delete",
                                                        Toast.LENGTH_SHORT);
                                            }
                                        }
                                    });
                        } else {
                            deleteCheck = true;
                            Toast.makeText(Settings.this, "Are you sure? All app account data will be deleted (not Spotify account)",
                                    Toast.LENGTH_SHORT);
                        }
                    }
                }
        );
        editAccountBtn = findViewById(R.id.editAccountButton);
        editAccountBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Settings.this,editAccount.class);
                        startActivity(i);
                    }
                }
        );

    }
}
