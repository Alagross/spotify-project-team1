package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.OkHttpClient;



public class myWrapped extends AppCompatActivity {
    ImageButton settingsBtn;
    Button duoWrappedBtn;
    Button exploreBtn;
    Button streamBtn;

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
