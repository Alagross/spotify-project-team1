import android.content.Intent;
import android.os.Bundle;
import android.view.View; // Add this import statement
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull; // Add this import statement
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrappeda1.R;
import com.example.spotifywrappeda1.myWrapped;
import com.google.android.gms.tasks.OnFailureListener; // Add these import statements
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAppProfile extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonRegister;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Add semicolon here
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        db = FirebaseFirestore.getInstance();

        // Set click listener for the Register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validate username and password
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateAppProfile.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform registration logic (e.g., save to Firebase)
                    Map<String, Object> user = new HashMap<>();
                    user.put("App Username", username);
                    user.put("App Password", password);

                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    // Registration successful, show toast and navigate
                                    Toast.makeText(CreateAppProfile.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    navigateToMyWrappedActivity();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Registration failed, show toast
                                    Toast.makeText(CreateAppProfile.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    // Method to redirect to MyWrappedActivity
    private void navigateToMyWrappedActivity() {
        // Create intent to navigate to MyWrapped activity
        // Replace MyWrappedActivity.class with the actual class name of your target activity
        Intent intent = new Intent(CreateAppProfile.this, myWrapped.class);
        startActivity(intent);
    }
}

//ToDo: We also need to store the token/specific spotify info in there somewhere
