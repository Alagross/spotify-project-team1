package com.example.spotifywrappeda1;
import com.google.gson.Gson;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SpotifyCalls {
    public String convertTrackUrisToJson(List<String> trackUris) {
        Gson gson = new Gson();
        String jsonInputString = gson.toJson(trackUris);
        return "{\"uris\": " + jsonInputString + "}";
    }
    public String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception or return a fallback value
            return "";
        }
    }
    public String convertTrackUrisToJsonArray(List<String> trackUris) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < trackUris.size(); i++) {
            sb.append("\"").append(trackUris.get(i)).append("\"");
            if (i < trackUris.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return "{\"uris\":" + sb.toString() + "}";
    }
    public void getCurrentUserProfile(String accessToken) {
        String url = "https://api.spotify.com/v1/me";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response (JSON format)
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTopTracks(String accessToken) {
        String url = "https://api.spotify.com/v1/me/top/tracks";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response (JSON format)
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createPlaylist(String accessToken, String userId, String playlistName) {
        String url = "https://api.spotify.com/v1/users/" + userId + "/playlists";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"name\": \"" + playlistName + "\", \"public\": false}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response (JSON format)
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addTracksToPlaylist(String accessToken, String playlistId, List<String> trackUris) {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"uris\": " + new Gson().toJson(trackUris) + "}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Tracks added successfully to the playlist.");
            } else {
                System.out.println("POST request to add tracks not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAlbumTracks(String accessToken, String albumId) {
        String url = "https://api.spotify.com/v1/albums/" + albumId + "/tracks";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request for album tracks not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchItem(String accessToken, String query, String type) {
        String url = null;
        try {
            url = "https://api.spotify.com/v1/search?q=" + URLEncoder.encode(query, "UTF-8") + "&type=" + type;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request for search not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRecentlyPlayedTracks(String accessToken) {
        String url = "https://api.spotify.com/v1/me/player/recently-played";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request for recently played tracks not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getArtistInformation(String accessToken, String artistId) {
        String url = "https://api.spotify.com/v1/artists/" + artistId;
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request for artist information not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playTrack(String accessToken, String deviceId, String trackUri) {
        String url = "https://api.spotify.com/v1/me/player/play";
        if (deviceId != null && !deviceId.isEmpty()) {
            url += "?device_id=" + deviceId;
        }
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonBody = "{\"uris\": [\"" + trackUri + "\"]}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Playback started successfully.");
            } else {
                System.out.println("PUT request to start playback not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveTrackToLibrary(String accessToken, String trackId) {
        String url = "https://api.spotify.com/v1/me/tracks?ids=" + trackId;
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Track saved successfully to the library.");
            } else {
                System.out.println("PUT request to save track not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setVolume(String accessToken, int volumePercent, String deviceId) {
        String url = "https://api.spotify.com/v1/me/player/volume?volume_percent=" + volumePercent;
        if (deviceId != null && !deviceId.isEmpty()) {
            url += "&device_id=" + deviceId;
        }

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) { // 204 is success
                System.out.println("Volume set successfully.");
            } else {
                System.out.println("PUT request to set volume not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } public void getUsersPlaylists(String accessToken) {
        String url = "https://api.spotify.com/v1/me/playlists";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request to get user's playlists not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } public void followPlaylist(String accessToken, String playlistId) {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/followers";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonBody = "{\"public\": true}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) { // 204 is success
                System.out.println("Successfully followed the playlist.");
            } else {
                System.out.println("PUT request to follow playlist not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void followArtist(String accessToken, String artistId) {
        String url = "https://api.spotify.com/v1/me/following?type=artist&ids=" + artistId;

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) { // 204 is success
                System.out.println("Successfully followed the artist.");
            } else {
                System.out.println("PUT request to follow artist not successful. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchSong(String accessToken, String songName) {
        try {
            String encodedSongName = URLEncoder.encode(songName, "UTF-8");
            String url = "https://api.spotify.com/v1/search?q=" + encodedSongName + "&type=track&limit=1";

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            System.out.println("Sending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Print the response which contains information about the song
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    public static void main(String[] args) {
        SpotifyApiExample example = new SpotifyApiExample();
        String accessToken = "YOUR_ACCESS_TOKEN"; // Replace with your actual access token
        String songName = "Imagine"; // Replace with the song you're searching for

        example.searchSong(accessToken, songName);
    }
    */


}
