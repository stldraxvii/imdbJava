package com.example.imdb.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 * Created by Matt on 6/17/2017.
 */

@Entity
public class Film {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String year;

    private String plot;
    private String poster;

    @ManyToMany
    private List<Country> countries;
    @ManyToMany
    private List<Director> directors;
    @ManyToMany
    private List<Actor> actors;
    @ManyToMany
    private List<Genre> genres;

    @NotNull
    private String imdbRatingString;
    private double imdbRating;

    public String metaRatingString;
    private double metaRating;
    private String rottenRatingString;
    private double rottenRating;

    @NotNull
    private String imdbId;

    @NotNull
    private String tmdbRatingString;
    private double tmdbRating;

    private double average;
    private int count;
    private double total;

    public Film (String name, String year, String plot, String poster, List<Country> countries, List<Director> directors,
                 List<Actor> actors, List<Genre> genres, String imdbRatingString, String metaRatingString, String rottenRatingString,
                 String imdbId, String tmdbRatingString) {
        this();
        this.name = name;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.countries = countries;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
        this.imdbRatingString = imdbRatingString;
        this.metaRatingString = metaRatingString;
        this.rottenRatingString = rottenRatingString;
        this.imdbId = imdbId;
        this.tmdbRatingString = tmdbRatingString;
    }

    public Film () { }

    public int getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public String getPlot() {return plot;}
    public void setPlot(String plot) {this.plot = plot;}

    public String getPoster() {return poster;}
    public void setPoster(String poster) {this.poster = poster;}

    public List<Country> getCountries() {return countries;}
    public List<Director> getDirectors() {return directors;}
    public List<Actor> getActors() {return actors;}
    public List<Genre> getGenres() {return genres;}

    public String getImdbRatingString() {return imdbRatingString;}
    public void setImdbRatingString(String imdbRatingString) {this.imdbRatingString = imdbRatingString;}

    public String getMetaRatingString() {return metaRatingString;}
    public void setMetaRatingString(String metaRatingString) {this.metaRatingString = metaRatingString;}

    public String getRottenRatingString() {return rottenRatingString;}
    public void setRottenRatingString(String rottenRatingString) {this.rottenRatingString = rottenRatingString;}

    public String getImdbId() {return imdbId;}
    public void setImdbId(String imdbId) {this.imdbId = imdbId;}

    public String getTmdbRatingString() {return tmdbRatingString;}
    public void setTmdbRatingString(String tmdbRatingString) {this.tmdbRatingString = tmdbRatingString;}

    public double getImdbRating() {return imdbRating;}
    public double getMetaRating() {return metaRating;}
    public double getRottenRating() {return rottenRating;}
    public double getTmdbRating() {return tmdbRating;}

    public double getAverage() {return average;}
    public void setAverage() {
        count = 1;
        total = 0;
        this.total += imdbRating;

        if (!this.metaRatingString.equals("N/A")) {
            this.total += metaRating;
            this.count += 1;
        }

        if (!this.rottenRatingString.equals("N/A")) {
            this.total += rottenRating;
            this.count += 1;
        }

        if (!this.tmdbRatingString.equals("N/A")) {
            this.total += tmdbRating;
            this.count += 1;
        }
        average = round((total/count),3);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void setRatings () {
        this.imdbRating = Double.parseDouble(imdbRatingString);

        if (!this.metaRatingString.equals("N/A")) {
            this.metaRating = Double.parseDouble(metaRatingString)/10;
        }
        if (!this.rottenRatingString.equals("N/A")) {
            this.rottenRating = Double.parseDouble(rottenRatingString)/10;
        }
        if (this.tmdbRatingString.equals("0.0")) {this.tmdbRatingString = "N/A";}
        else {
            this.tmdbRating=round(Double.parseDouble(tmdbRatingString),1);
        }
    }




}
