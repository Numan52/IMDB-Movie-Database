module com.example.movieapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires ormlite.jdbc;
    requires com.h2database;
    requires java.sql;

    opens com.example.movieapp.Database to ormlite.jdbc;
    opens com.example.movieapp to javafx.fxml, com.google.gson;
    exports com.example.movieapp;
    exports com.example.movieapp.Controllers;
    opens com.example.movieapp.Controllers to com.google.gson, javafx.fxml;
    exports com.example.movieapp.UI;
    opens com.example.movieapp.UI to com.google.gson, javafx.fxml;
    exports com.example.movieapp.Movie;
    opens com.example.movieapp.Movie to com.google.gson, javafx.fxml;
}