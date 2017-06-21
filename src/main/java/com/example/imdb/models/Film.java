package com.example.imdb.models;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by Matt on 6/17/2017.
 */
public class Film {

    @NotNull
    private String name;

    @NotNull
    private String year;

    private String plot;
    private String poster;

    @NotNull
    private RatingImdb imdbRating;

    public RatingMeta metaRating;
    private RatingRotten rottenRating;

    @NotNull
    private String imdbId;

    @NotNull
    private RatingImdb tmdbRating;

    private double average;
    private int count;
    private double total;

    private int filmId;
    private static int nextId = 1;

    public Film (String name, String year, String plot, String poster, RatingImdb imdbRating,
                 RatingMeta metaRating, RatingRotten rottenRating, String imdbId, RatingImdb tmdbRating) {
        this();
        this.name = name;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.imdbRating = imdbRating;
        this.metaRating = metaRating;
        this.rottenRating = rottenRating;
        this.imdbId = imdbId;
        this.tmdbRating = tmdbRating;
    }

    public Film () {
        filmId = nextId;
        nextId++;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

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

    public RatingImdb getTmdbRating() {return tmdbRating;}
    public void setTmdbRating(RatingImdb tmdbRating) {this.tmdbRating = tmdbRating;}

    public int getFilmId() {return filmId;}
    public void setFilmId(int filmId) {this.filmId = filmId;}

    public double getAverage() {return average;}
    public void setAverage() {
        count = 2;
        total = 0;
        this.total += imdbRating.mathRating + tmdbRating.mathRating;

        if (!this.metaRating.rating.equals("N/A")) {
            this.total += metaRating.mathRating;
            this.count += 1;
        }

        if (!this.rottenRating.rating.equals("N/A")) {
            this.total += rottenRating.mathRating;
            this.count += 1;
        }
        average = round((total/count),2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }




}
