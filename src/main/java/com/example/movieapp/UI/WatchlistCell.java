package com.example.movieapp.UI;

import com.example.movieapp.ClickEventHandler;
import com.example.movieapp.Database.WatchlistMovieEntity;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WatchlistCell extends ListCell<WatchlistMovieEntity> {
    private final Label movieTitle = new Label();
    private final Label description = new Label();
    private final Label genres = new Label();
    private final Button showDetails = new Button("Show Details");
    private final Button removeWatchlist = new Button("Remove");

    private final Region spacer = new Region();
    private final HBox header = new HBox(movieTitle, spacer, showDetails, removeWatchlist);
    private final VBox layout = new VBox(header, description, genres);
    public WatchlistCell(ClickEventHandler<WatchlistMovieEntity> onRemoveFromWatchlistClicked) {
        super();
        removeWatchlist.setOnMouseClicked(event -> {
            onRemoveFromWatchlistClicked.onClick(getItem());
        });
        movieTitle.getStyleClass().add("text-yellow");
        movieTitle.setFont(Font.font(16));
        description.getStyleClass().add("text-white");
        description.setMaxWidth(850);
        description.setWrapText(true);
        genres.getStyleClass().add("text-white");

        layout.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.8), null, null)));
        //layout.setMaxWidth(this.getScene().getWidth());

        layout.setSpacing(10);
        layout.setPadding(new Insets(10));
        layout.setFillWidth(true);
        //layout.alignmentProperty().set(Pos.CENTER_LEFT);
        layout.setAlignment(Pos.CENTER_LEFT);

        header.setHgrow(spacer, Priority.ALWAYS);
        HBox.setMargin(showDetails, new Insets(0, 10, 0, 0));
    }

    @Override
    public void updateItem(WatchlistMovieEntity movie, boolean empty) {
        super.updateItem(movie, empty);
        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            movieTitle.setText(movie.getTitle());
            description.setText(movie.getDescription());
            genres.setText(movie.getGenres().toString());

            setGraphic(layout);
        }
    }

}
