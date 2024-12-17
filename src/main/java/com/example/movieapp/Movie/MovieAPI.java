package com.example.movieapp.Movie;
import okhttp3.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MovieAPI {
    private final static String URL = "https://prog2.fh-campuswien.ac.at/movies";
    private URLBuilder urlBuilder;
    private String query;
    private Genre genre;
    private String releaseYear;
    private String rating;

    private Gson gson = new Gson();
    private OkHttpClient client = new OkHttpClient();
    private Response response;

    public MovieAPI(String query, Genre genre, String releaseYear, String rating) {
        this.query = query;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public List<Movie> getAllMovies() throws IOException {
        urlBuilder = new URLBuilder(URL);
        Request request = new Request.Builder()
                .url(urlBuilder
                        .query(query)
                        .genre(genre)
                        .releaseYear(releaseYear)
                        .rating(rating)
                        .buildURL())
                .header("User-Agent", "http.agent")
                .get()
                .build();
        System.out.println(urlBuilder.buildURL());
        try (Response response = client.newCall(request).execute()) {
            // Ensure the response is successful
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(Objects.requireNonNull(response.body()).string(), Movie[].class);
            return List.of(movies);
        }
    }

    public static void main(String[] args) throws IOException {

    }
}