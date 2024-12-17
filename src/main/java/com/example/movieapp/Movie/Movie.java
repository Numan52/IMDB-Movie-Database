package com.example.movieapp.Movie;

import java.util.*;

public class Movie {
    private final String id;
    private final String title;
    private final String description;
    private final List<Genre> genres;
    private final int releaseYear;
    private final String imgUrl;
    private final int lengthInMinutes; // in minutes
    private final List<String> directors = new ArrayList<>();
    private final List<String> writers = new ArrayList<>();
    private final List<String> mainCast = new ArrayList<>();
    private final double rating; // 0-10

    public Movie(String id, String title, String description, List<Genre> genres, float rating, int releaseYear, String imgUrl, int lengthInMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie(String title, String description, List<Genre> genres, float rating, int releaseYear) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.imgUrl = "";
        this.lengthInMinutes = 0;
    }

    public static List<Movie> initializeMovie() {
        List<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie
                ("Parasite",
                "Alle Arbeitslosen, Ki-taek Familie nimmt eigentümliche Interesse an den wohlhabenden und glamourösen Parks für Ihren Lebensunterhalt, bis Sie in einem unerwarteten Vorfall verstrickt.",
                Arrays.asList(Genre.DRAMA, Genre.THRILLER),
                8.5f,
                2019));
        movieList.add(new Movie
                ( "King Kong",
                "In New York 1933 zwingt ein übermäßig ehrgeiziger Filmproduzent seine Darsteller und angestellte Schiffsmannschaft zu der geheimnisvollen Skull Island zu reisen, in dem sie Kong antreffen, ein riesiger Affe, der sich sofort in die führender Dame Ann Darrow verliebt.",
                Arrays.asList(Genre.ADVENTURE, Genre.ROMANCE, Genre.ACTION),
                7.2f,
                2005));
        movieList.add(new Movie
                ("The Wolf of Wall Street",
                "Beruhend auf der wahren Geschichte von Jordan Belfort, von seinem Aufstieg als reicher Börsenmakler, der sein Leben in Saus und Braus genießt, bis zu seinem Absturz in die Kriminalität, Korruption und die Verfolgung durch die US-Bundesregierung.",
                Arrays.asList(Genre.DRAMA, Genre.THRILLER, Genre.BIOGRAPHY),
                8.2f,
                2013));
        movieList.add(new Movie
                ("Avatar",
                "Ein querschnittgelähmter US-Marinesoldat wird auf eine ganz besondere Mission zum Mond Pandora geschickt, auf der er hin- und hergerissen ist, zwischen striktem Befehlsgehorsam und dem Wunsch, die Welt zu retten, die seine neue Heimat ist.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY),
                7.0f,
                2009));

        return movieList;
    }

    public String getId() {return id; }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }
    public String getGenresString() {
        StringBuilder genres = new StringBuilder();
        genres.append(this.getGenres().get(0));
        for (int i = 1; i < this.getGenres().size(); i++) {
            genres.append(", ").append(this.getGenres().get(i));
        }


        return genres.toString();
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public double getRating() {
        return rating;
    }



    public static class SortAscending implements Comparator<Movie> {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            return movie1.getTitle().compareTo(movie2.getTitle());
        }
    }

    public static class SortDescending implements Comparator<Movie> {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            return movie2.getTitle().compareTo(movie1.getTitle());
        }
    }

}
