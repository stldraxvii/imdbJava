package com.example.imdb.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Matt on 6/17/2017.
 */
public class FilmForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    public String title;

    @NotNull
    public String year;

    public String plot;

    public String poster;

    @NotNull
    public RatingImdb imdbRating;

    public RatingMeta metaRating;
    public RatingRotten rottenRating;

    @NotNull
    public String imdbId;

    public String getTitle() {return title;}
    public void setTitle(String name) {this.title = name;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public String getPlot() {return plot;}
    public void setPlot(String plot) {this.plot = plot;}

    public String getPoster() {return poster;}
    public void setPoster(String poster) {this.poster = poster;}

    public RatingImdb getImdbRating() {return imdbRating;}
    public void setImdbRating(RatingImdb imdbRating) {this.imdbRating = imdbRating;}

    public RatingMeta getMetaRating() {return metaRating;}
    public void setMetaRating(RatingMeta metaRating) {this.metaRating = metaRating;}

    public RatingRotten getRottenRating() {return rottenRating;}
    public void setRottenRating(RatingRotten rottenRating) {this.rottenRating = rottenRating;}

    public String getImdbId() {return imdbId;}
    public void setImdbId(String imdbId) {this.imdbId = imdbId;}

    public FilmForm (String title, String year, String plot, String poster,
                     RatingImdb imdbRating, RatingMeta metaRating, RatingRotten rottenRating, String imdbId) {
        this();
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.imdbRating = imdbRating;
        this.metaRating = metaRating;
        this.rottenRating = rottenRating;
        this.imdbId = imdbId;
    }

    public FilmForm () {

    }
}
