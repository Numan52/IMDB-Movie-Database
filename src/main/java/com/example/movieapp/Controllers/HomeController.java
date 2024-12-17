package com.example.movieapp.Controllers;

import com.example.movieapp.*;
import com.example.movieapp.Database.WatchlistRepository;
import com.example.movieapp.Movie.Genre;
import com.example.movieapp.Movie.Movie;
import com.example.movieapp.Movie.MovieAPI;
import com.example.movieapp.ObserverPattern.Observer;
import com.example.movieapp.StatePattern.NotSortedState;
import com.example.movieapp.StatePattern.SortedState;
import com.example.movieapp.UI.MovieCell;
import com.example.movieapp.UI.PopUpMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable, Observer {

    public Button switchWatchlistBtn;
    public BorderPane borderPane;
    MovieAPI movieAPI = new MovieAPI(null, null, null, null);
    public TextField textField;
    public ComboBox<Genre> genreComboBox;
    public ComboBox ratingComboBox;
    public ComboBox releaseComboBox;
    public Button filterButton;
    public Button sortButton;
    public ListView<Movie> movieListView;
    public Button resetButton;
    private SortedState sortedState;

    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();

    private static boolean observerRegistered = false;

    private final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {

        try {
            watchlistRepository.addToWatchlist(clickedItem);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(observerRegistered);
        if (!observerRegistered) {
            watchlistRepository.addObserver(this);
            observerRegistered = true;
        }


        movieListView.setStyle("-fx-background-color: black");
        borderPane.setStyle("-fx-background-color: black");

        genreComboBox.getItems().addAll(Genre.values());
        try {
            observableMovies.addAll(movieAPI.getAllMovies());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Integer[] years = new Integer[125];
        for (int i = 0; i < years.length; i++) {
            years[i] = 1900 + i;
        }
        releaseComboBox.getItems().add("No Filter");
        releaseComboBox.setPromptText("Release Year");
        releaseComboBox.getItems().addAll(years);

        genreComboBox.setValue(Genre.ALL);

        ratingComboBox.getItems().add("No Filter");
        ratingComboBox.setPromptText("Rating");
        Integer[] ratings = new Integer[11];
        for (int i = 0; i < ratings.length; i++) {
            ratings[i] = i;
        }
        ratingComboBox.getItems().addAll(ratings);

        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked));

        sortedState = new NotSortedState();

        textField.setOnAction(event -> filterMovies());
        switchWatchlistBtn.setOnAction(event -> switchToWatchlist());
        filterButton.setOnAction(event -> filterMovies());
        sortButton.setOnAction(event -> sortMovies());
        resetButton.setOnAction(event -> resetDefault());
    }

    private void resetDefault() {
        observableMovies.clear();
        observableMovies.addAll(getMovies(null, null, null, null));
        sortButton.setText("Sort");
        genreComboBox.setValue(Genre.ALL);
        textField.setText("");
    }

    private void sortMovies() {
        sortedState = sortedState.changeState();
        sortedState.sort(observableMovies);
    }

    public List<Movie> getMovies(String query, Genre genre, String releaseYear, String rating) {
        movieAPI = new MovieAPI(query, genre, releaseYear, rating);
        try {
            return movieAPI.getAllMovies();
        } catch (IOException e) {
            throw new RuntimeException("Error while API request" + e);
        }
    }

    public void filterMovies() {
        String query = textField.getText() == null ? null : textField.getText().toLowerCase();
        Genre genre = genreComboBox.getValue() == null || genreComboBox.getValue() == Genre.ALL ? null : genreComboBox.getValue();
        String releaseYear = releaseComboBox.getValue() == null || releaseComboBox.getValue().equals("No Filter")
                                    ? null : String.valueOf(releaseComboBox.getValue());
        String rating;
        if (ratingComboBox.getValue() == null || ratingComboBox.getValue().equals("No Filter")) {
            rating = null;
        } else {
            rating = String.valueOf(ratingComboBox.getValue());
        }

        observableMovies.clear();
        observableMovies.addAll(getMovies(query, genre, releaseYear, rating));
        sortedState.sort(observableMovies);
    }

    public void switchToWatchlist() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/movieapp/watchlist.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            newScene.getStylesheets().add((MovieApplication.class.getResource("/com/example/movieapp/styles.css")).toExternalForm());
            Stage currentStage = (Stage) borderPane.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMostPopularActor(List<Movie> movies) {
        List<String> allActors = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.toList());

        Map<String, Long> actorFrequencyMap = allActors.stream()
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        String mostPopularActor = actorFrequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey())
                .orElse(null);

        return mostPopularActor;
    }


    @Override
    public void update(String message) {
        PopUpMessage popUpMessage = new PopUpMessage("Info", message);
        popUpMessage.show();
    }
}