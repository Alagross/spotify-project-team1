package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;


import okhttp3.OkHttpClient;



public class myWrapped extends AppCompatActivity {
    ImageButton settingsBtn;
    Button pastWrapsBtn;
    Button personalityBtn;
    Button recommendBtn;
    Button gameBtn;

    public TextView tokenTextView, codeTextView, profileTextView;

    private String mAccessToken, mAccessCode;
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wrapped);

        Button btnShortTerm = findViewById(R.id.buttonTopTracksShortTerm);
        Button btnMediumTerm = findViewById(R.id.buttonTopTracksMediumTerm);
        Button btnLongTerm = findViewById(R.id.buttonTopTracksLongTerm);

//        btnShortTerm.setOnClickListener(v -> fetchTopItems("short_term"));
//        btnMediumTerm.setOnClickListener(v -> fetchTopItems("medium_term"));
//        btnLongTerm.setOnClickListener(v -> fetchTopItems("long_term"));


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

        pastWrapsBtn = findViewById(R.id.pastWrapsButton);
        pastWrapsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, pastWraps.class);
                        startActivity(i);
                    }
                }
        );
        personalityBtn = findViewById(R.id.personalityButton);
        personalityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, Personality.class);
                        startActivity(i);
                    }
                }
        );
        recommendBtn = findViewById(R.id.recommendButton);
        recommendBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, Recommend.class);
                        startActivity(i);
                    }
                }
        );
        gameBtn = findViewById(R.id.gameButton);
        gameBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(myWrapped.this, Game.class);
                        startActivity(i);
                    }
                }
        );


    }

//    private void fetchTopItems(String timeRange) {
//        if (mAccessToken == null) {
//            Toast.makeText(this, "Access Token is not available. Please get the token first.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String url = "https://api.spotify.com/v1/me/top/tracks?time_range=" + timeRange;
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Authorization", "Bearer " + mAccessToken)
//                .build();
//
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
//
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                runOnUiThread(() -> profileTextView.setText("Failed to fetch data: " + e.getMessage()));
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String jsonData = response.body().string();
//                    runOnUiThread(() -> profileTextView.setText(jsonData));
//                } else {
//                    runOnUiThread(() -> profileTextView.setText("Error: " + response.code()));
//                }
//            }
//        });
//    }





//    }



}
