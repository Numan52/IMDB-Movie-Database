package com.example.movieapp;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {


    @Override
    public Object call(Class<?> aClass) {
        try{
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
