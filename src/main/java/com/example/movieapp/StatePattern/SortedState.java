package com.example.movieapp.StatePattern;

import com.example.movieapp.Movie.Movie;

import java.util.List;

public interface SortedState {
    List<Movie> sort(List<Movie> movies);
    SortedState changeState();
}
