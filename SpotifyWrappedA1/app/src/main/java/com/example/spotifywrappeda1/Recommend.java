package com.example.spotifywrappeda1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Recommend extends AppCompatActivity {
    ImageButton settingsBtn;
    Button myWrappedBtn;
    Button pastWrapsBtn;
    Button personalityBtn;
    Button gameBtn;
    RecyclerView recommendations;
    String mAccessToken = MainActivity.mAccessToken();
    String artistId;
    private static final String BASE_URL = "https://api.spotify.com/v1/recommendations";
    private OkHttpClient client = new OkHttpClient();;
    private static final String TAG = "Recommend";

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);

        getTopArtist(mAccessToken);




        settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Recommend.this,Settings.class);
                        startActivity(i);
                    }
                }
        );
        myWrappedBtn = findViewById(R.id.myWrappedButton);
        myWrappedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Recommend.this, myWrapped.class);
                        startActivity(i);
                    }
                }
        );
        pastWrapsBtn = findViewById(R.id.pastWrapsButton);
        pastWrapsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Recommend.this, pastWraps.class);
                        startActivity(i);
                    }
                }
        );
        personalityBtn = findViewById(R.id.personalityButton);
        personalityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Recommend.this, Personality.class);
                        startActivity(i);
                    }
                }
        );
        gameBtn = findViewById(R.id.gameButton);
        gameBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Recommend.this, Game.class);
                        startActivity(i);
                    }
                }
        );

    }
    public void getRecommendations(String accessToken, String artistId) {
        String url = BASE_URL + "?seed_artists=" + artistId;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray tracks = jsonObject.getJSONArray("tracks");
                        List<String> recommendationsList = new ArrayList<>();
                        for (int i = 0; i < tracks.length(); i++) {
                            JSONObject track = tracks.getJSONObject(i);
                            String trackName = track.getString("name");
                            recommendationsList.add(trackName);
                        }
                        // Update the RecyclerView with recommendations
                        runOnUiThread(() -> {
                            RecyclerView recyclerView = findViewById(R.id.recommendRecyclerView);
                            RecommendAdapter adapter = new RecommendAdapter(recommendationsList);
                            recyclerView.setAdapter(adapter);
                        });
                    } catch (JSONException e) {
                        Log.e(TAG, "Failed to parse JSON response", e);
                    }
                } else {
                    Log.e(TAG, "Failed to fetch recommendations, code: " + response.code());
                    // Handle unsuccessful response
                }
            }
        });
    }
    public void getTopArtist(String accessToken) {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "Failed to fetch top artist", e);
                // Handle failure
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray items = jsonObject.getJSONArray("items");
                        if (items.length() > 0) {
                            JSONObject topArtist = items.getJSONObject(0); // Assuming we choose the first artist
                            String artistName = topArtist.getString("name");
                            String artistId = topArtist.getString("id");
                            getRecommendations(mAccessToken, artistId);
                            Log.d(TAG, "Top artist: " + artistName);
                        } else {
                            Log.e(TAG, "No top artist found");
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "Failed to parse JSON response", e);
                    }
                } else {
                    Log.e(TAG, "Failed to fetch top artist, code: " + response.code());
                    // Handle unsuccessful response
                }
            }
        });
    }
}
