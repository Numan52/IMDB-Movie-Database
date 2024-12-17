package com.example.movieapp.Database;

import com.example.movieapp.Movie.Movie;
import com.example.movieapp.ObserverPattern.Observable;
import com.example.movieapp.ObserverPattern.Observer;
import com.example.movieapp.UI.PopUpMessage;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {
    Dao<WatchlistMovieEntity, Long> dao;
    private static WatchlistRepository instance;

    private List<Observer> observers = new ArrayList<>();

    private WatchlistRepository() {
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchlist(Movie movie) throws SQLException {
        List<WatchlistMovieEntity> movieInDB =  dao.queryForEq("title", movie.getTitle());
        if (!movieInDB.isEmpty()) {
            notifyObservers("Selected movie already in Watchlist: " + movie.getTitle());
            return;
        }
        WatchlistMovieEntity watchlistMovieEntity = movieToEntity(movie);
        dao.create(watchlistMovieEntity);
        notifyObservers("Movie successfully added to Watchlist");
    }

    public void removeFromWatchlist(WatchlistMovieEntity movie) throws SQLException {
        dao.delete(movie);
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return dao.queryForAll();
    }

    public static WatchlistRepository getInstance() {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    private WatchlistMovieEntity movieToEntity(Movie movie) {
        return new WatchlistMovieEntity("0", movie.getTitle(), movie.getDescription(), movie.getGenresString(), movie.getReleaseYear(),
                movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
            System.out.println(observer.toString());
        }

    }
}
