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
    public String imdbRatingString;

    public String metaRatingString;
    public String rottenRatingString;

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

    public String getImdbRatingString() {return imdbRatingString;}
    public void setImdbRatingString(String imdbRatingString) {this.imdbRatingString = imdbRatingString;}

    public String getMetaRatingString() {return metaRatingString;}
    public void setMetaRatingString(String metaRatingString) {this.metaRatingString = metaRatingString;}

    public String getRottenRatingString() {return rottenRatingString;}
    public void setRottenRatingString(String rottenRatingString) {this.rottenRatingString = rottenRatingString;}

    public String getImdbId() {return imdbId;}
    public void setImdbId(String imdbId) {this.imdbId = imdbId;}

    public FilmForm (String title, String year, String plot, String poster,
                     String imdbRatingString, String metaRatingString, String rottenRatingString, String imdbId) {
        this();
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.imdbRatingString = imdbRatingString;
        this.metaRatingString = metaRatingString;
        this.rottenRatingString = rottenRatingString;
        this.imdbId = imdbId;
    }

    public FilmForm () {

    }
}
