package com.example.movieapp.Movie;

public class URLBuilder {
    private StringBuilder url;

    public URLBuilder(String url) {
        this.url = new StringBuilder(url);
    }

    public URLBuilder query(String query) {
        if (query != null && !query.isEmpty()) {
            url.append(getDelimiter()).append("query=").append(query);
        }
        return this;
    }

    public URLBuilder genre(Genre genre) {
        if (genre != null) {
            url.append(getDelimiter()).append("genre=").append(genre);
        }
        return this;
    }

    public URLBuilder releaseYear(String releaseYear) {
        if (releaseYear != null) {
            url.append(getDelimiter()).append("releaseYear=").append(releaseYear);
        }
        return this;
    }

    public URLBuilder rating(String rating) {
        if (rating != null) {
            url.append(getDelimiter()).append("ratingFrom=").append(rating);
        }
        return this;
    }

    public String getDelimiter() {
        if (url.indexOf("?") == -1) {
            return "?";
        } else {
            return "&";
        }
    }

    public String buildURL() {
        return url.toString();
    }
}
