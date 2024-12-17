package com.example.movieapp;

@FunctionalInterface
public interface ClickEventHandler<T> {
    void onClick(T t);
}
