package com.example.movieapp.StatePattern;

import com.example.movieapp.Movie.Movie;

import java.util.Collections;
import java.util.List;

public class SortedAscendingState implements SortedState{
    @Override
    public List<Movie> sort(List<Movie> movies) {
        Collections.sort(movies, new Movie.SortAscending());
        return movies;
    }

    @Override
    public SortedState changeState() {
        return new SortedDescendingState();
    }
}
