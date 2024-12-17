package com.example.movieapp.Controllers;

import com.example.movieapp.ClickEventHandler;
import com.example.movieapp.Database.WatchlistMovieEntity;
import com.example.movieapp.Database.WatchlistRepository;
import com.example.movieapp.MovieApplication;
import com.example.movieapp.UI.WatchlistCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {

    public ListView movieListView;
    public Button switchHomeBtn;
    public AnchorPane anchorPane;
    private ObservableList<WatchlistMovieEntity> observableMovies = FXCollections.observableArrayList();
    WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();

    private final ClickEventHandler<WatchlistMovieEntity> onRemoveFromWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.removeFromWatchlist(clickedItem);
            observableMovies.remove(clickedItem);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieListView.setStyle("-fx-background-color: black;");

        switchHomeBtn.setOnAction(event -> switchToHome());
        try {
            getMoviesFromDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new WatchlistCell(onRemoveFromWatchlistClicked));
    }

    public void getMoviesFromDB() throws SQLException {
        observableMovies.addAll(watchlistRepository.getWatchlist());
    }

    public void switchToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/movieapp/home-view.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            newScene.getStylesheets().add((MovieApplication.class.getResource("/com/example/movieapp/styles.css")).toExternalForm());
            Stage currentStage = (Stage) anchorPane.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
