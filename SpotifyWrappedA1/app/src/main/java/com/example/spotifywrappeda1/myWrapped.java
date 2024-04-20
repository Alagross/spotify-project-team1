package com.example.spotifywrappeda1;

import static com.example.spotifywrappeda1.MainActivity.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class myWrapped extends MainActivity {
    ImageButton settingsBtn;
    Button duoWrappedBtn;
    Button exploreBtn;
    Button streamBtn;

    private static final String SCOPES = "user-top-read";

    public String mAccessToken = MainActivity.mAccessToken();

    public TextView tokenTextView, codeTextView, profileTextView;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private Call mCall;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wrapped);

        profileTextView = findViewById(R.id.textViewResults);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.topMargin = getResources().getDisplayMetrics().heightPixels / 3;

        profileTextView.setMovementMethod(new ScrollingMovementMethod());
        profileTextView.setLayoutParams(params);


        Button btnShortTerm = findViewById(R.id.buttonTopTracksShortTerm);
        Button btnMediumTerm = findViewById(R.id.buttonTopTracksMediumTerm);
        Button btnLongTerm = findViewById(R.id.buttonTopTracksLongTerm);

        btnShortTerm.setOnClickListener(v -> fetchTopItems("short_term", SCOPES));
        btnMediumTerm.setOnClickListener(v -> fetchTopItems("medium_term", SCOPES));
        btnLongTerm.setOnClickListener(v -> fetchTopItems("long_term", SCOPES));

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

    private void fetchTopItems(String timeRange, String scopes) {
        if (mAccessToken == null) {
            Toast.makeText(this, "Access Token is not available. Please get the token first.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://api.spotify.com/v1/me/top/tracks?time_range=" + timeRange + "&scope=" + scopes;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(jsonData);
                        JSONArray itemsArray = jsonObject.getJSONArray("items");

                        StringBuilder formattedData = new StringBuilder();
                        for (int i = 0; i < itemsArray.length(); i++) {
                            JSONObject item = itemsArray.getJSONObject(i);
                            String trackName = item.getString("name");
                            JSONArray artistsArray = item.getJSONArray("artists");
                            String artistName = artistsArray.getJSONObject(0).getString("name");
                            formattedData.append("Track: ").append(trackName).append("\n");
                            formattedData.append("Artist: ").append(artistName).append("\n\n");
                        }

                        formattedData.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        final String formattedJsonData = formattedData.toString();


                        runOnUiThread(() -> profileTextView.setText(formattedJsonData));


                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> profileTextView.setText("Failed to parse JSON: " + e.getMessage()));
                    }
                } else {
                    runOnUiThread(() -> profileTextView.setText("Error: " + response.code()));
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> profileTextView.setText("Failed to fetch data: " + e.getMessage()));
            }
        });
    }
}


