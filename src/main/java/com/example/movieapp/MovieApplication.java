package com.example.movieapp;

import com.example.movieapp.Controllers.HomeController;
import com.example.movieapp.Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MovieApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MyFactory myFactory = new MyFactory();
        FXMLLoader loader = new FXMLLoader(MovieApplication.class.getResource("home-view.fxml"));
        loader.setControllerFactory(myFactory);

        //FXMLLoader fxmlLoader = new FXMLLoader(MovieApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add((MovieApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("Move Database");
        stage.setScene(scene);
        stage.show();

//        try {
//            Database.getDatabase().testDB();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    public static void main(String[] args) {
        launch();
    }
}