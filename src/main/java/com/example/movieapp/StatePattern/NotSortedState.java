package com.example.movieapp.StatePattern;

import com.example.movieapp.Movie.Movie;

import java.util.List;

public class NotSortedState implements SortedState{
    @Override
    public List<Movie> sort(List<Movie> movies) {
        return movies;
    }

    @Override
    public SortedState changeState() {
        return new SortedAscendingState();
    }
}
